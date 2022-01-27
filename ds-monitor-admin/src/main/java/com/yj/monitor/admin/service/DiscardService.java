package com.yj.monitor.admin.service;

import com.yj.monitor.admin.domain.RegisterCenter;
import com.yj.monitor.api.constant.RemoteAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author gaolei
 * @Date 2022/1/21 下午5:22
 * @Version 1.0
 */
@Service
public class DiscardService {

    private final Logger logger = LoggerFactory.getLogger(DiscardService.class);

    public String discardClient(String clientId){
        RegisterCenter.removeByClient(clientId);
        logger.warn(clientId + " 断开链接 ...");
        return RemoteAPI.DISCARD_CLIENT_RSP;
    }
}
