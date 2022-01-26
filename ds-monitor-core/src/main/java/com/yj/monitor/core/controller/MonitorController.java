package com.yj.monitor.core.controller;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.core.handler.JDKManagementHandler;
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
    private JDKManagementHandler JDKManagementHandler;

    @RequestMapping("memory")
    public String memory() {
        return JSON.toJSONString(JDKManagementHandler.getMemoryInfo());
    }

    @RequestMapping("gc")
    public String gc() {
        return JSON.toJSONString(JDKManagementHandler.getGcInfo());
    }

    @RequestMapping("runtime")
    public String runtime() {
        return JSON.toJSONString(JDKManagementHandler.getRuntimeInfo());
    }

    @RequestMapping("classLoad")
    public String classLoad() {
        return JSON.toJSONString(JDKManagementHandler.getClassLoader());
    }

    @RequestMapping("compilation")
    public String compilation() {
        return JSON.toJSONString(JDKManagementHandler.getCompilation());
    }

    @RequestMapping("operatingSystem")
    public String operatingSystemInfo() {
        return JSON.toJSONString(JDKManagementHandler.getOperatingSystemInfo());
    }

    @RequestMapping("systemProperties")
    public String systemProperties() {
        return JSON.toJSONString(JDKManagementHandler.getSystemProperties());
    }

    @RequestMapping("thread")
    public String thread() {
        return JSON.toJSONString(JDKManagementHandler.getThreadInfo());
    }

    @RequestMapping("memory-partition")
    public String memoryPartition() {
        return JSON.toJSONString(JDKManagementHandler.getMemoryPartition());
    }

}
