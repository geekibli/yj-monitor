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

    @Deprecated
    Method MEMORY = new Method("com.yj.monitor.core.handler.JDKManagementHandler", "getMemoryInfo", "内存参数");
    @Deprecated
    Method GC = new Method("com.yj.monitor.core.handler.JDKManagementHandler", "getGcInfo", "垃圾收集参数");
    @Deprecated
    Method RUNTIME = new Method("com.yj.monitor.core.handler.JDKManagementHandler", "getRuntimeInfo", "运行时参数");
    @Deprecated
    Method THREAD = new Method("com.yj.monitor.core.handler.JDKManagementHandler", "getThreadInfo", "线程参数");
    @Deprecated
    Method OPERATOR_SYSTEM = new Method("com.yj.monitor.core.handler.JDKManagementHandler", "getOperatingSystemInfo", "操作系统参数");
    @Deprecated
    Method SYSTEM_PROFILE = new Method("com.yj.monitor.core.handler.JDKManagementHandler", "getSystemProperties", "系统变量");
    @Deprecated
    Method CLASSLOADER = new Method("com.yj.monitor.core.handler.JDKManagementHandler", "getClassLoader", "类加载信息");
    @Deprecated
    Method MEMORY_PARTITION = new Method("com.yj.monitor.core.handler.JDKManagementHandler", "getMemoryPartition", "内存分区信息");

    @Deprecated
    Method JVM_GC_LIVE_DATA_SIZE = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcLiveDataSize", "Size of long-lived heap memory pool after reclamation");
    @Deprecated
    Method JVM_GC_MAX_DATA_SIZE = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcMaxDataSize", "Max size of long-lived heap memory pool");
    @Deprecated
    Method JVM_GC_MEMORY_ALLOCATED = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcMemoryAllocated", "Incremented for an increase in the size of the (young) heap memory pool after one GC to before the next");
    @Deprecated
    Method JVM_GC_MEMORY_PROMOTED = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcMemoryPromoted", "Count of positive increases in the size of the old generation memory pool before GC to after GC");
    @Deprecated
    Method JVM_GC_PAUSE = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcPause", "Time spent in GC pause");



    Method SERVER_BASE = new Method("com.yj.monitor.core.service.ServerService", "getServer", "获取服务器基本信息");

    List<Method> SCHEDULE_MONITOR_METHODS = Lists.newArrayList(
            MEMORY,
            GC,
            THREAD,
            CLASSLOADER,
            MEMORY_PARTITION,
            SERVER_BASE
    );




}
