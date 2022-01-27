package com.yj.monitor.admin.runner;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorThread;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.JmxThread;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rpc.MonitorApi;
import com.yj.monitor.api.rsp.Response;
import com.yj.monitor.rpc.client.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午5:09
 * @Version 1.0
 */
public class PullThreadTask implements Callable<MonitorThread> {

    private final Logger logger = LoggerFactory.getLogger(PullThreadTask.class);

    private final MonitorEvent monitorEvent;

    public PullThreadTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public MonitorThread call() throws Exception {
        return v2();
    }

    private MonitorThread v2() {
        if (monitorEvent == null || monitorEvent.notValid()) {
            return null;
        }

        MonitorApi monitorApi = new RpcClient(monitorEvent.getNode().getRpcAddress()).create(MonitorApi.class);
        JmxThread thread = monitorApi.getThread();
        MonitorThread mt = new MonitorThread();
        mt.setBatchId(monitorEvent.getBatchId());
        mt.setClientAddress(monitorEvent.getNode().getAddress());
        mt.setClientId(monitorEvent.getNode().getClientId());

        mt.setDaemonCount(Long.valueOf(thread.getDaemonThreadCount()));
        mt.setThreadCount(Long.valueOf(thread.getThreadCount()));
        mt.setTotalStartedCount(thread.getTotalStartedThreadCount());
        mt.setPeakCount(Long.valueOf(thread.getPeakThreadCount()));
        mt.setDeadlockThreads(thread.getDeadLockedThreads());
        return mt;
    }

    private MonitorThread v1() {
        if (monitorEvent == null || monitorEvent.notValid()) {
            return null;
        }

        HttpResponse response = HttpUtil.createPost(monitorEvent.getNode().getMonitorUrl())
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.THREAD.getcName(), MonitorMethods.THREAD.getmName(), new Object[0])))
                .execute();

        Response invokeRspVO = JSON.parseObject(response.body(), Response.class);
        Map<String, String> map = (Map<String, String>) invokeRspVO.getData();

        MonitorThread thread = new MonitorThread();
        thread.setBatchId(monitorEvent.getBatchId());
        thread.setClientAddress(monitorEvent.getNode().getAddress());
        thread.setClientId(monitorEvent.getNode().getClientId());
        thread.setDaemonCount(Long.valueOf(map.get("DaemonThreadCount")));
        thread.setTotalStartedCount(Long.valueOf(map.get("TotalStartedThreadCount")));
        thread.setPeakCount(Long.valueOf(map.get("PeakThreadCount")));
        thread.setThreadCount(Long.valueOf(map.get("ThreadCount")));
        String deadThreads = map.get("DeadlockedThreads");
        if (!"null".equals(deadThreads)) {
            thread.setDeadlockThreads(deadThreads);
        }
        return thread;
    }
}
