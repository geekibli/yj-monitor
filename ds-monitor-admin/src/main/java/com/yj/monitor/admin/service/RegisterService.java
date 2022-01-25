package com.yj.monitor.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.monitor.admin.domain.RegisterCenter;

import com.yj.monitor.admin.domain.req.ApplicationPageListReqVO;
import com.yj.monitor.admin.domain.rsp.ApplicationPageListRsp;
import com.yj.monitor.admin.domain.rsp.ApplicationPageListRspVO;
import com.yj.monitor.admin.entity.ClientRegisterTable;
import com.yj.monitor.admin.mapper.ext.ClientRegisterTableExtMapper;
import com.yj.monitor.admin.trans.RegisterTransaction;
import com.yj.monitor.api.domain.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author gaolei
 * @Date 2022/1/20 下午4:46
 * @Version 1.0
 */
@Service
public class RegisterService {

    private final Logger logger = LoggerFactory.getLogger(RegisterService.class);
    @Resource
    private PullMonitorService pullMonitorService;
    @Resource
    private RegisterTransaction registerTransaction;
    @Resource
    private ClientRegisterTableExtMapper clientRegisterTableExtMapper;

    /**
     * 注册客户端结点
     * 1、缓存中只存放正在线的数据
     * 2、数据库中存放所有注册过的结点数据
     *
     * @param node 客户端结点数据
     */
    public void register(Node node) {
        // 1、封装Node数据
        RegisterCenter.online(node.getClientId(), node);

        ClientRegisterTable table = new ClientRegisterTable();
        table.setApplicationName(node.getApplicationName());
        table.setClientAddress(node.getAddress());
        table.setClientId(node.getClientId());
        table.setSystemType(node.getSystemType());
        registerTransaction.registerClient(node.getClientId(), table);

//        if (1 == RegisterCenter.onlineCount()) {
//            pullMonitorService.execute();
//        }
    }


    public ApplicationPageListRspVO pageList(ApplicationPageListReqVO reqVO) {
        PageInfo<ClientRegisterTable> pageInfo = PageHelper.startPage(reqVO.getPageNum(), reqVO.getPageSize())
                .doSelectPageInfo(() -> clientRegisterTableExtMapper.pageList(reqVO.getClientId(), reqVO.getApplicationName()));

        if (null == pageInfo || CollectionUtils.isEmpty(pageInfo.getList())) {
            return new ApplicationPageListRspVO(new ArrayList<>(), 0L, 0);
        }

        List<ClientRegisterTable> tableList = pageInfo.getList();
        List<ApplicationPageListRsp> appList = tableList.stream()
                .map(table -> {
                    ApplicationPageListRsp app = new ApplicationPageListRsp();
                    BeanUtils.copyProperties(table, app);
                    app.setOnline(RegisterCenter.isOnline(table.getClientId()));
                    return app;
                }).collect(Collectors.toList());

        ApplicationPageListRspVO result = new ApplicationPageListRspVO();
        result.setApplicationList(appList);
        result.setTotal(pageInfo.getTotal());
        result.setPage(pageInfo.getPages());
        return result;
    }


    public void setAddress(String clientId, String address) {
        clientRegisterTableExtMapper.setAddress(clientId, address);
    }

}
