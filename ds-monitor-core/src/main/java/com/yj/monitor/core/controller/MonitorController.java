package com.yj.monitor.core.controller;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.core.handler.MonitorHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author gaolei
 * @Date 2022/1/24 下午2:07
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "monitor")
public class MonitorController {

    @RequestMapping("memory")
    public String memory() {
        return JSON.toJSONString(MonitorHandler.getMemoryInfo());
    }

    @RequestMapping("gc")
    public String gc() {
        return JSON.toJSONString(MonitorHandler.getGcInfo());
    }

    @RequestMapping("runtime")
    public String runtime() {
        return JSON.toJSONString(MonitorHandler.getRuntimeInfo());
    }

    @RequestMapping("classLoad")
    public String classLoad() {
        return JSON.toJSONString(MonitorHandler.getClassLoader());
    }

    @RequestMapping("compilation")
    public String compilation() {
        return JSON.toJSONString(MonitorHandler.getCompilation());
    }

    @RequestMapping("operatingSystem")
    public String operatingSystemInfo() {
        return JSON.toJSONString(MonitorHandler.getOperatingSystemInfo());
    }

    @RequestMapping("systemProperties")
    public String systemProperties() {
        return JSON.toJSONString(MonitorHandler.getSystemProperties());
    }

    @RequestMapping("thread")
    public String thread() {
        return JSON.toJSONString(MonitorHandler.getThreadInfo());
    }

    @RequestMapping("memory-partition")
    public String memoryPartition() {
        return JSON.toJSONString(MonitorHandler.getMemoryPartition());
    }

}
