package com.yj.monitor.admin.runner;

import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorServer;
import com.yj.monitor.admin.mapper.MonitorServerMapper;
import com.yj.monitor.api.domain.Server;
import com.yj.monitor.api.rpc.MonitorApi;
import com.yj.monitor.rpc.client.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * @Author gaolei
 * @Date 2022/1/28 下午2:18
 * @Version 1.0
 */
public class PullServerTask implements Callable<MonitorServer> {

    private final Logger logger = LoggerFactory.getLogger(PullServerTask.class);

    private final MonitorEvent event;

    @Resource
    private MonitorServerMapper monitorServerMapper;

    public PullServerTask(MonitorEvent event) {
        this.event = event;
    }

    @Override
    public MonitorServer call() {
        if (event == null || event.notValid()) {
            return null;
        }

        MonitorApi monitorApi = new RpcClient(event.getNode().getRpcAddress()).create(MonitorApi.class);
        Server serverInfo = monitorApi.getServerInfo();
        if (serverInfo == null) {
            return null;
        }

        MonitorServer ms = new MonitorServer();
        ms.setBatchId(event.getBatchId());
        ms.setClientAddress(event.getNode().getClientUrl());
        ms.setClientId(event.getNode().getClientId());

        ms.setCpuFree(serverInfo.getCpuFree());
        ms.setCpuSysUsage(serverInfo.getCpuSysUsage());
        ms.setCpuTotalUsage(serverInfo.getCpuTotalUsage());
        ms.setCpuUserUsage(serverInfo.getCpuUserUsage());
        ms.setCpuWait(serverInfo.getCpuWait());

        ms.setSystemCpuUsage(serverInfo.getSystemCpuUsage());
        ms.setSystemLoadAverage(serverInfo.getSystemLoadAverage());

        ms.setDiskFree(serverInfo.getDiskFree());
        ms.setDiskThreshold(serverInfo.getDiskThreshold());
        ms.setDiskTotal(serverInfo.getDiskTotal());
        ms.setServerHost(serverInfo.getServerHost());
        return ms;
    }

}
