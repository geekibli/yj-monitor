package com.yj.monitor.api.constant;

import com.yj.monitor.api.domain.Method;


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


    Method JVM_GC_LIVE_DATA_SIZE = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcLiveDataSize", "Size of long-lived heap memory pool after reclamation");
    Method JVM_GC_MAX_DATA_SIZE = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcMaxDataSize", "Max size of long-lived heap memory pool");
    Method JVM_GC_MEMORY_ALLOCATED = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcMemoryAllocated", "Incremented for an increase in the size of the (young) heap memory pool after one GC to before the next");
    Method JVM_GC_MEMORY_PROMOTED = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcMemoryPromoted", "Count of positive increases in the size of the old generation memory pool before GC to after GC");
    Method JVM_GC_PAUSE = new Method("com.yj.monitor.core.service.ActuatorMetricService", "getGcPause", "Time spent in GC pause");


}
