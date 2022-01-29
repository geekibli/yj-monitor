package com.yj.monitor.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.monitor.admin.domain.req.ExceptionPageListReqVO;
import com.yj.monitor.admin.domain.req.ThreadpolylineReqVO;
import com.yj.monitor.admin.domain.rsp.ExceptionLogPageListRspVO;
import com.yj.monitor.admin.domain.rsp.ThreadPolylineRspVO;
import com.yj.monitor.admin.entity.MonitorExceptionLog;
import com.yj.monitor.admin.mapper.ext.MonitorExceptionLogExtMapper;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午2:23
 * @Version 1.0
 */
@Service
public class ExceptionService {

    private final Logger logger = LoggerFactory.getLogger(ExceptionService.class);

    @Resource
    private MonitorExceptionLogExtMapper monitorExceptionLogExtMapper;

    public ExceptionLogPageListRspVO pageList(ExceptionPageListReqVO reqVO) {

        PageInfo<MonitorExceptionLog> pageInfo = PageHelper.startPage(reqVO.getPageNum(), reqVO.getPageSize())
                .doSelectPageInfo(() -> {
                    monitorExceptionLogExtMapper.pageList(reqVO.getClassName(), reqVO.getMethodName());
                });

        if (pageInfo == null || CollectionUtils.isEmpty(pageInfo.getList())) {
            return new ExceptionLogPageListRspVO(Lists.emptyList(), 0, 0L);
        }

        ExceptionLogPageListRspVO rspVO = new ExceptionLogPageListRspVO();
        rspVO.setLogList(pageInfo.getList());
        rspVO.setPage(pageInfo.getPages());
        rspVO.setTotal(pageInfo.getTotal());
        return rspVO;
    }





}
