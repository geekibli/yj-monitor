package com.yj.monitor.admin.runner;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorGc;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.Method;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rsp.RemoteInvokeRspVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午7:56
 * @Version 1.0
 */
public class PullGcTask implements Callable<MonitorGc> {

    private final Logger logger = LoggerFactory.getLogger(PullGcTask.class);

    private MonitorEvent monitorEvent;

    public PullGcTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public MonitorGc call() throws Exception {
        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }


        String body = remoteInvoke(MonitorMethods.GC);
        logger.info("gc {}", body);


        MonitorGc gc = new MonitorGc();
        gc.setBatchId(monitorEvent.getBatchId());
        gc.setClientAddress(monitorEvent.getClient().getAddress());
        gc.setClientId(monitorEvent.getClient().getClientId());

        RemoteInvokeRspVO invokeRspVO = JSON.parseObject(body, RemoteInvokeRspVO.class);
        Map<String, Map<String, Long>> map = (Map<String, Map<String, Long>>) invokeRspVO.getData();

        Map<String, Long> sweepMap = map.get("PS MarkSweep");
        if (null != sweepMap && !sweepMap.isEmpty()) {
            gc.setPsMarksweepCollectionCount(sweepMap.get("CollectionTime"));
            gc.setPsMarksweepCollectionTime(sweepMap.get("CollectionCount"));
        }


        Map<String, Long> scavengeMap = map.get("PS Scavenge");
        if (null != scavengeMap && !scavengeMap.isEmpty()) {
            gc.setPsScavengeCollectionCount(scavengeMap.get("CollectionCount"));
            gc.setPsScavengeCollectionTime(scavengeMap.get("CollectionTime"));
        }

        gc.setLiveDataSize(getLiveDataSize());
        gc.setMaxDataSize(getMaxDataSize());
        gc.setMemoryAllocatedCount(getMemoryAllocated());
        gc.setMemoryPromotedCount(getMemoryPromoted());

        Map<String, Long> pauseMap = getGcPause();
        gc.setPauseCount(pauseMap.get("COUNT"));
        gc.setPauseMax(pauseMap.get("MAX"));
        // TODO
        if (null != pauseMap.get("TOTAL_TIME")) {
            gc.setPauseTotalTime(new BigDecimal(pauseMap.get("TOTAL_TIME")).doubleValue());
        }


        return gc;
    }


    public Long getLiveDataSize() {
        String body = remoteInvoke(MonitorMethods.JVM_GC_LIVE_DATA_SIZE);
        if (StringUtils.isBlank(body)) {
            return 0L;
        }

        List<Measurement> measurements = JSON.parseObject(body).getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }

        return measurements.get(0).getValue();
    }

    public Long getMaxDataSize() {
        String body = remoteInvoke(MonitorMethods.JVM_GC_MAX_DATA_SIZE);

        List<Measurement> measurements = JSON.parseObject(body).getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }

        return measurements.get(0).getValue();
    }

    public Long getMemoryAllocated() {
        String body = remoteInvoke(MonitorMethods.JVM_GC_MEMORY_ALLOCATED);

        List<Measurement> measurements = JSON.parseObject(body).getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }

        return measurements.get(0).getValue();
    }

    public Long getMemoryPromoted() {
        String body = remoteInvoke(MonitorMethods.JVM_GC_MEMORY_PROMOTED);

        List<Measurement> measurements = JSON.parseObject(body).getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }

        return measurements.get(0).getValue();
    }

    public Map<String, Long> getGcPause() {
        String body = remoteInvoke(MonitorMethods.JVM_GC_MEMORY_PROMOTED);

        List<Measurement> measurements = JSON.parseObject(body).getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }

        return measurements.stream().collect(Collectors.toMap(Measurement::getStatistic, Measurement::getValue, (o1, o2) -> o1));
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


    private String remoteInvoke(Method method) {
        HttpResponse response = HttpUtil.createPost(monitorEvent.getClient().getMonitorUrl())
                .header("Content-Type", "application/json")
                .body(JSON.toJSONString(new RemoteMonitorReqVO(method.getcName(), method.getmName(), new Object[0])))
                .execute();
        return response.body();
    }


}
