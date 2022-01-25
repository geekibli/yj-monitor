package com.yj.monitor.core.controller;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.core.handler.MonitorHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Author gaolei
 * @Date 2022/1/24 下午2:07
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "monitor")
public class MonitorController {

    @Resource
    private MonitorHandler monitorHandler;

    @RequestMapping("memory")
    public String memory() {
        return JSON.toJSONString(monitorHandler.getMemoryInfo());
    }

    @RequestMapping("gc")
    public String gc() {
        return JSON.toJSONString(monitorHandler.getGcInfo());
    }

    @RequestMapping("runtime")
    public String runtime() {
        return JSON.toJSONString(monitorHandler.getRuntimeInfo());
    }

    @RequestMapping("classLoad")
    public String classLoad() {
        return JSON.toJSONString(monitorHandler.getClassLoader());
    }

    @RequestMapping("compilation")
    public String compilation() {
        return JSON.toJSONString(monitorHandler.getCompilation());
    }

    @RequestMapping("operatingSystem")
    public String operatingSystemInfo() {
        return JSON.toJSONString(monitorHandler.getOperatingSystemInfo());
    }

    @RequestMapping("systemProperties")
    public String systemProperties() {
        return JSON.toJSONString(monitorHandler.getSystemProperties());
    }

    @RequestMapping("thread")
    public String thread() {
        return JSON.toJSONString(monitorHandler.getThreadInfo());
    }

    @RequestMapping("memory-partition")
    public String memoryPartition() {
        return JSON.toJSONString(monitorHandler.getMemoryPartition());
    }

}
