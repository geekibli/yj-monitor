package com.yj.monitor.core.handler;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yj.monitor.api.constant.MetricName;
import com.yj.monitor.api.domain.Disk;
import com.yj.monitor.api.domain.Measurement;
import com.yj.monitor.api.rsp.Response;
import com.yj.monitor.core.config.MonitorConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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


    public String getSystemCpuUsage(Integer port) {
        String metric = metric(MetricName.SYSTEM_CPU_USAGE, port);
        List<Measurement> measurements = JSON.parseObject(metric).getJSONArray("measurements").toJavaList(Measurement.class);
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
