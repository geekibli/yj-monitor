package com.yj.monitor.api.constant;

import com.google.common.collect.Lists;
import com.yj.monitor.api.domain.Method;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午6:00
 * @Version 1.0
 */
public interface MonitorMethods {

    Method MEMORY = new Method("com.yj.monitor.core.handler.MonitorHandler", "getMemoryInfo", "内存参数");
    Method GC = new Method("com.yj.monitor.core.handler.MonitorHandler", "getGcInfo", "垃圾收集参数");
    Method RUNTIME = new Method("com.yj.monitor.core.handler.MonitorHandler", "getRuntimeInfo", "运行时参数");
    Method THREAD = new Method("com.yj.monitor.core.handler.MonitorHandler", "getThreadInfo", "线程参数");
    Method OPERATOR_SYSTEM = new Method("com.yj.monitor.core.handler.MonitorHandler", "getOperatingSystemInfo", "操作系统参数");
    Method SYSTEM_PROFILE = new Method("com.yj.monitor.core.handler.MonitorHandler", "getSystemProperties", "系统变量");
    Method CLASSLOADER = new Method("com.yj.monitor.core.handler.MonitorHandler", "getClassLoader", "类加载信息");
    Method MEMORY_PARTITION = new Method("com.yj.monitor.core.handler.MonitorHandler", "getMemoryPartition", "内存分区信息");

    List<Method> MONITOR_METHODS = Lists.newArrayList(
            MEMORY, GC, RUNTIME, THREAD, OPERATOR_SYSTEM, SYSTEM_PROFILE, CLASSLOADER
    );


}
