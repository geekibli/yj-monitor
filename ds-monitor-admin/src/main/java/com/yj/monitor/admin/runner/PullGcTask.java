package com.yj.monitor.admin.runner;

import cn.hutool.http.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorGc;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.GarbageCollection;
import com.yj.monitor.api.domain.Method;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rpc.MonitorApi;
import com.yj.monitor.api.rsp.Response;
import com.yj.monitor.rpc.client.RpcClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午7:56
 * @Version 1.0
 */
public class PullGcTask implements Callable<MonitorGc> {

    private final Logger logger = LoggerFactory.getLogger(PullGcTask.class);

    private final MonitorEvent monitorEvent;

    public PullGcTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public MonitorGc call() {
        return v2();
    }

    private MonitorGc v2() {
        if (monitorEvent == null || monitorEvent.notValid()) {
            return null;
        }

        MonitorApi monitorApi = new RpcClient(monitorEvent.getNode().getRpcAddress()).create(MonitorApi.class);
        GarbageCollection gcInfo = monitorApi.getGcInfo();
        MonitorGc mgc = new MonitorGc();
        mgc.setBatchId(monitorEvent.getBatchId());
        mgc.setClientAddress(monitorEvent.getNode().getClientUrl());
        mgc.setClientId(monitorEvent.getNode().getClientId());
        mgc.setPsMarksweepCollectionCount(gcInfo.getPsMarksweepCollectionCount().intValue());
        mgc.setPsMarksweepCollectionTime(gcInfo.getPsMarksweepCollectionTime().intValue());
        mgc.setPsScavengeCollectionCount(gcInfo.getPsScavengeCollectionCount().intValue());
        mgc.setPsScavengeCollectionTime(gcInfo.getPsScavengeCollectionTime().intValue());
        mgc.setLiveDataSize(gcInfo.getLiveDataSize());
        mgc.setMaxDataSize(gcInfo.getMaxDataSize());
        mgc.setMemoryAllocatedCount(gcInfo.getMemoryAllocatedCount());
        mgc.setMemoryPromotedCount(gcInfo.getMemoryPromotedCount());
        mgc.setPauseCount(gcInfo.getPauseCount());
        mgc.setPauseMax(gcInfo.getPauseMax());
        mgc.setPauseTotalTime(mgc.getPauseTotalTime());
        return mgc;
    }

    private MonitorGc v1() {
        if (monitorEvent == null || monitorEvent.notValid()) {
            return null;
        }

        String body = remoteInvoke(monitorEvent.getNode().getMonitorUrl(), MonitorMethods.GC, null);

        MonitorGc gc = new MonitorGc();
        gc.setBatchId(monitorEvent.getBatchId());
        gc.setClientAddress(monitorEvent.getNode().getClientUrl());
        gc.setClientId(monitorEvent.getNode().getClientId());

        Response invokeRspVO = JSON.parseObject(body, Response.class);
        Map<String, Map<String, Integer>> map = (Map<String, Map<String, Integer>>) invokeRspVO.getData();

        Map<String, Integer> sweepMap = map.get("PS MarkSweep");
        if (null != sweepMap && !sweepMap.isEmpty()) {
            gc.setPsMarksweepCollectionCount(Optional.ofNullable(sweepMap.get("CollectionTime")).orElse(0));
            gc.setPsMarksweepCollectionTime(Optional.ofNullable(sweepMap.get("CollectionCount")).orElse(0));
        }

        Map<String, Integer> scavengeMap = map.get("PS Scavenge");
        if (null != scavengeMap && !scavengeMap.isEmpty()) {
            gc.setPsScavengeCollectionCount(Optional.ofNullable(scavengeMap.get("CollectionCount")).orElse(0));
            gc.setPsScavengeCollectionTime(Optional.ofNullable(scavengeMap.get("CollectionTime")).orElse(0));
        }

        gc.setLiveDataSize(getLiveDataSize());
        gc.setMaxDataSize(getMaxDataSize());
        gc.setMemoryAllocatedCount(getMemoryAllocated());
        gc.setMemoryPromotedCount(getMemoryPromoted());

        Map<String, Long> pauseMap = getGcPause(monitorEvent.getNode().getClientPort());
        gc.setPauseCount(pauseMap.get("COUNT"));
        gc.setPauseMax(pauseMap.get("MAX"));
        // TODO
        if (null != pauseMap.get("TOTAL_TIME")) {
            gc.setPauseTotalTime(new BigDecimal(pauseMap.get("TOTAL_TIME")).doubleValue());
        }
        return gc;
    }

    public Long getLiveDataSize() {
        return getMeasurementFirstVal(MonitorMethods.JVM_GC_LIVE_DATA_SIZE, monitorEvent.getNode().getClientPort());
    }

    public Long getMaxDataSize() {
        return getMeasurementFirstVal(MonitorMethods.JVM_GC_MAX_DATA_SIZE, monitorEvent.getNode().getClientPort());
    }

    public Long getMemoryAllocated() {
        return getMeasurementFirstVal(MonitorMethods.JVM_GC_MEMORY_ALLOCATED, monitorEvent.getNode().getClientPort());
    }

    public Long getMemoryPromoted() {
        return getMeasurementFirstVal(MonitorMethods.JVM_GC_MEMORY_PROMOTED, monitorEvent.getNode().getClientPort());
    }

    private Long getMeasurementFirstVal(Method method, Integer clientPort) {
        Object[] param = new Object[1];
        param[0] = clientPort;
        String body = remoteInvoke(monitorEvent.getNode().getActuatorMetricsUrl(), method, param);
        if (StringUtils.isEmpty(body)) {
            throw new RuntimeException();
        }
        JSONObject bodyJson = JSON.parseObject(body);
        if (HttpStatus.HTTP_OK != bodyJson.getInteger("code")) {
            throw new RuntimeException();
        }

        JSONObject data = bodyJson.getJSONObject("data");
        List<Measurement> measurements = data.getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }
        return measurements.get(0).getValue();
    }

    public Map<String, Long> getGcPause(Integer clientPort) {
        Object[] param = new Object[1];
        param[0] = clientPort;
        String body = remoteInvoke(monitorEvent.getNode().getActuatorMetricsUrl(), MonitorMethods.JVM_GC_PAUSE, param);
        JSONObject data = JSON.parseObject(body).getJSONObject("data");
        List<Measurement> measurements = data.getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }
        return measurements.stream()
                .collect(Collectors.toMap(Measurement::getStatistic, Measurement::getValue, (o1, o2) -> o1));
    }


    static class Measurement {
        private String statistic;
        private Long value;

        public String getStatistic() {
            return statistic;
        }

        public void setStatistic(String statistic) {
            this.statistic = statistic;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }

    // TODO
    private String remoteInvoke(String url, Method method, Object[] param) {
        HttpResponse response = HttpUtil.createPost(url)
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .body(JSON.toJSONString(new RemoteMonitorReqVO(method.getcName(), method.getmName(), param)))
                .execute();
        return response.body();
    }

}
