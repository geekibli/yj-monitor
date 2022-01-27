package com.yj.monitor.core.handler;

import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yj.monitor.api.constant.MetricName;
import com.yj.monitor.api.domain.Disk;
import com.yj.monitor.api.domain.Measurement;
import com.yj.monitor.api.domain.Method;
import com.yj.monitor.api.rsp.Response;
import com.yj.monitor.core.config.MonitorConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author gaolei
 * @Date 2022/1/26 下午12:13
 * @Version 1.0
 */
@Component
public class SpringBootActuatorHandler {

    @Resource
    private MonitorConfig monitorConfig;


    public Response getGcLiveDataSize(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_LIVE_DATA_SIZE, port));
    }

    public Long getGcLiveDataSizeVal(Integer port) {
        BigDecimal val = getMeasurementFirstVal(MetricName.JVM_GC_LIVE_DATA_SIZE, port);
        return val == null ? 0L : val.longValue();
    }

    public Response getGcMaxDataSize(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_MAX_DATA_SIZE, port));
    }

    public Long getGcMaxDataSizeVal(Integer port) {
        BigDecimal val = getMeasurementFirstVal(MetricName.JVM_GC_MAX_DATA_SIZE, port);
        return val == null ? 0L : val.longValue();
    }

    public Response getGcMemoryAllocated(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_MEMORY_ALLOCATED, port));
    }

    public Long getGcMemoryAllocatedVal(Integer port) {
        BigDecimal val = getMeasurementFirstVal(MetricName.JVM_GC_MEMORY_ALLOCATED, port);
        return val == null ? 0L : val.longValue();
    }

    public Response getGcMemoryPromoted(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_MEMORY_PROMOTED, port));
    }

    public Long getGcMemoryPromotedVal(Integer port) {
        BigDecimal val = getMeasurementFirstVal(MetricName.JVM_GC_MEMORY_PROMOTED, port);
        return val == null ? 0L : val.longValue();
    }

    public Response getGcPause(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_PAUSE, port));
    }

    public Map<String, BigDecimal> getGcPauseMap(Integer port) {
        String body = metric(MetricName.JVM_GC_PAUSE, port);
        if (StringUtils.isEmpty(body)) {
            return null;
        }
        List<Measurement> measurements = JSON.parseObject(body).getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }
        return measurements.stream()
                .collect(Collectors.toMap(Measurement::getStatistic, Measurement::getValue, (o1, o2) -> o1));
    }


    /**
     * 只允许本地调用
     *
     * @param name 指标名称
     * @return json
     */
    public String metric(String name, Integer port) {
        return HttpUtil.get("http://localhost:" + port + "/actuator/metrics/" + name);
    }


    public String getSystemCpuUsageVal(Integer port) {
        return Objects.requireNonNull(getMeasurementFirstVal(MetricName.SYSTEM_CPU_USAGE, port)).toString();
    }


    private BigDecimal getMeasurementFirstVal(String metricName, Integer port) {
        String body = metric(metricName, port);
        List<Measurement> measurements = JSON.parseObject(body).getJSONArray("measurements").toJavaList(Measurement.class);
        if (measurements.isEmpty()) {
            return null;
        }
        return measurements.get(0).getValue();
    }

    public JSONObject invokeApi(String path) {
        String response = HttpUtil.get("http://localhost:10000/actuator" + path);
        return JSON.parseObject(response);
    }


    public Disk getDisk() {
        JSONObject response = invokeApi("/health/diskSpace");
        JSONObject details = response.getJSONObject("details");
        Disk disk = new Disk();
        disk.setTotal(details.getLong("total"));
        disk.setFree(details.getLong("free"));
        disk.setThreshold(details.getLong("threshold"));
        return disk;
    }


}
