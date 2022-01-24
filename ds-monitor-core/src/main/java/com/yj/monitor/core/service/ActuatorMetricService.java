package com.yj.monitor.core.service;

import cn.hutool.http.HttpUtil;
import com.yj.monitor.api.constant.MetricName;
import com.yj.monitor.api.rsp.RemoteInvokeRspVO;
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


    public RemoteInvokeRspVO getGcLiveDataSize() {
        return RemoteInvokeRspVO.successData(metric(MetricName.JVM_GC_LIVE_DATA_SIZE));
    }

    public RemoteInvokeRspVO getGcMaxDataSize() {
        return RemoteInvokeRspVO.successData(metric(MetricName.JVM_GC_MAX_DATA_SIZE));
    }

    public RemoteInvokeRspVO getGcMemoryAllocated() {
        return RemoteInvokeRspVO.successData(metric(MetricName.JVM_GC_MEMORY_ALLOCATED));
    }

    public RemoteInvokeRspVO getGcMemoryPromoted() {
        return RemoteInvokeRspVO.successData(metric(MetricName.JVM_GC_MEMORY_PROMOTED));
    }

    public RemoteInvokeRspVO getGcPause() {
        return RemoteInvokeRspVO.successData(metric(MetricName.JVM_GC_PAUSE));
    }

    /**
     * 只允许本地调用
     *
     * @param name 指标名称
     * @return
     */
    private String metric(String name) {
        return HttpUtil.get("http://localhost:" + monitorConfig.getApplicationPort() + "/" + name);
    }
}
