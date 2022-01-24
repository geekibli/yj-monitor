package com.yj.monitor.admin.service;

import com.yj.monitor.admin.doman.ClientContainer;

import com.yj.monitor.api.constant.RemoteAPI;
import com.yj.monitor.api.domain.Client;
import com.yj.monitor.api.req.ClientRegisterReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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

    public void register(ClientRegisterReqVO reqVO) {
        Client client = new Client(reqVO.getClientHost(), reqVO.getClientPort());
        client.setMonitorUrl("http://" + reqVO.getClientHost() + ":" + reqVO.getClientPort() + RemoteAPI.MONITOR_PULL);
        client.setClientId(reqVO.getClientId());

        ClientContainer.online(reqVO.getClientId(), client);

        if (1 == ClientContainer.onlineCount()) {
            pullMonitorService.execute();
        }
    }
}
