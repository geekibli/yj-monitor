package com.yj.monitor.admin.runner;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorThread;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.Client;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rsp.RemoteInvokeRspVO;
import org.apache.commons.lang3.StringUtils;
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
        if (null == monitorEvent || null == monitorEvent.getBatchId() || null == monitorEvent.getClient()) {
            return null;
        }

        HttpResponse response = HttpUtil.createPost(monitorEvent.getClient().getMonitorUrl())
                .header("Content-Type", "application/json")
                .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.THREAD.getcName(), MonitorMethods.THREAD.getmName(), new Object[0])))
                .execute();
        logger.info("thread {}", response.body());

        RemoteInvokeRspVO invokeRspVO = JSON.parseObject(response.body(), RemoteInvokeRspVO.class);

        Map<String, String> map = (Map<String, String>) invokeRspVO.getData();

        MonitorThread thread = new MonitorThread();

        thread.setBatchId(monitorEvent.getBatchId());
        thread.setClientAddress(monitorEvent.getClient().getAddress());
        thread.setClientId(monitorEvent.getClient().getClientId());

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


    public static void main(String[] args) throws Exception {
        MonitorEvent event = new MonitorEvent();
        event.setBatchId(1485562321706885123L);
        Client client = new Client();
        client.setAddress("/127.0.0.1:58499");
        client.setClientId("yjmonitorcore001");
        client.setMonitorUrl("http://192.168.50.154:10000/monitor");
        client.setHost("192.168.50.154");
        client.setPort(10000);
        event.setClient(client);
        MonitorThread call = new PullThreadTask(event).call();
        System.err.println("call " + JSON.toJSONString(call));


    }


}
