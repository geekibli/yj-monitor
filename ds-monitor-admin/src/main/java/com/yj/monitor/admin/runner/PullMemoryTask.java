package com.yj.monitor.admin.runner;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorMemory;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.Mem;
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
 * @Date 2022/1/20 下午5:06
 * @Version 1.0
 */
public class PullMemoryTask implements Callable<MonitorMemory> {
    private final Logger logger = LoggerFactory.getLogger(PullMemoryTask.class);

    private final MonitorEvent monitorEvent;

    public PullMemoryTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public MonitorMemory call() {
        return v2();
    }


    private MonitorMemory v2() {
        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }

        MonitorApi monitorApi = new RpcClient(monitorEvent.getNode().getRpcAddress()).create(MonitorApi.class);
        Mem memInfo = monitorApi.getMemInfo();

        MonitorMemory memory = new MonitorMemory();
        memory.setBatchId(monitorEvent.getBatchId());
        memory.setClientAddress(monitorEvent.getNode().getAddress());
        memory.setClientId(monitorEvent.getNode().getClientId());

        memory.setHeapCommitted(memInfo.getHeapCommitted());
        memory.setHeapMax(memInfo.getHeapMax());
        memory.setHeapInit(memInfo.getHeapInit());
        memory.setHeapUsed(memInfo.getHeapUsed());
        memory.setNonHeapCommitted(memInfo.getNonHeapCommitted());
        memory.setNonHeapInit(memInfo.getNonHeapInit());
        memory.setNonHeapMax(memInfo.getNonHeapMax());
        memory.setNonHeapUsed(memInfo.getNonHeapUsed());
        memory.setAvailable(memInfo.getAvailable());
        memory.setTotal(memInfo.getTotal());
        memory.setSwapPagesIn(memInfo.getSwapPagesIn());
        memory.setSwapPagesOut(memInfo.getSwapPagesOut());
        memory.setSwapTotal(memInfo.getSwapTotal());
        memory.setSwapUsed(memInfo.getSwapUsed());
        memory.setPageSize(memInfo.getPageSize());
        return memory;
    }


    private MonitorMemory v1() {
        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }

        HttpResponse response = HttpUtil.createPost(monitorEvent.getNode().getMonitorUrl())
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.MEMORY.getcName(), MonitorMethods.MEMORY.getmName(), new Object[0])))
                .execute();

        Response invokeRspVO = JSON.parseObject(response.body(), Response.class);
        Map<String, String> map = (Map<String, String>) invokeRspVO.getData();

        MonitorMemory memory = new MonitorMemory();
        memory.setBatchId(monitorEvent.getBatchId());
        memory.setClientAddress(monitorEvent.getNode().getAddress());
        memory.setClientId(monitorEvent.getNode().getClientId());

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
