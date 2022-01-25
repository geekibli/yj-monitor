package com.yj.monitor.core.service;

import cn.hutool.http.HttpUtil;
import com.yj.monitor.api.constant.MetricName;
import com.yj.monitor.api.rsp.Response;
import com.yj.monitor.core.config.MonitorConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午10:28
 * @Version 1.0
 */
@Service
public class ActuatorMetricService {

    @Resource
    private MonitorConfig monitorConfig;


    public Response getGcLiveDataSize(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_LIVE_DATA_SIZE, port));
    }

    public Response getGcMaxDataSize(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_MAX_DATA_SIZE, port));
    }

    public Response getGcMemoryAllocated(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_MEMORY_ALLOCATED, port));
    }

    public Response getGcMemoryPromoted(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_MEMORY_PROMOTED, port));
    }

    public Response getGcPause(Integer port) {
        return Response.successData(metric(MetricName.JVM_GC_PAUSE, port));
    }

    /**
     * 只允许本地调用
     *
     * @param name 指标名称
     * @return
     */
    private String metric(String name, Integer port) {
        return HttpUtil.get("http://localhost:" + port + "/actuator/metrics/" + name);
    }
}
