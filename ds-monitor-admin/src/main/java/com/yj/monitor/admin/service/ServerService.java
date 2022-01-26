package com.yj.monitor.admin.service;

import com.yj.monitor.admin.handler.RemoteInvokeHandler;
import com.yj.monitor.api.domain.Server;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午7:45
 * @Version 1.0 服务器相关逻辑 admin
 */
@Service
public class ServerService {

    @Resource
    private RemoteInvokeHandler remoteInvokeHandler;


    public Server getServer() {

        return null;

    }


}
