package com.yj.monitor.admin.runner;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorMemory;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rsp.RemoteInvokeRspVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午5:06
 * @Version 1.0
 */
public class PullMemoryTask implements Callable<MonitorMemory> {
    private final Logger logger = LoggerFactory.getLogger(PullMemoryTask.class);


    private MonitorEvent monitorEvent;

    public PullMemoryTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public MonitorMemory call() {
        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }

        HttpResponse response = HttpUtil.createPost(monitorEvent.getClient().getMonitorUrl())
                .header("Content-Type", "application/json")
                .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.MEMORY.getcName(), MonitorMethods.MEMORY.getmName(), new Object[0])))
                .execute();
        logger.info("memory {}", response.body());

        RemoteInvokeRspVO invokeRspVO = JSON.parseObject(response.body(), RemoteInvokeRspVO.class);

        Map<String,String> map = (Map<String,String>)invokeRspVO.getData();

        MonitorMemory memory = new MonitorMemory();
        memory.setBatchId(monitorEvent.getBatchId());
        memory.setClientAddress(monitorEvent.getClient().getAddress());
        memory.setClientId(monitorEvent.getClient().getClientId());

        memory.setHeapCommitted(Long.valueOf(map.get("heap.committed")));
        memory.setHeapMax(Long.valueOf(map.get("heap.max")));
        memory.setHeapInit(Long.valueOf(map.get("heap.init")));
        memory.setHeapUsed(Long.valueOf(map.get("heap.used")));
        memory.setNonHeapCommitted(Long.valueOf(map.get("nonHeap.committed")));
        memory.setNonHeapInit(Long.valueOf(map.get("nonHeap.init")));
        memory.setNonHeapMax(Long.valueOf(map.get("nonHeap.max")));
        memory.setNonHeapUsed(Long.valueOf(map.get("nonHeap.used")));
        return memory;
    }

}
