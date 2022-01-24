package com.yj.monitor.api.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午10:10
 * @Version 1.0
 */
public interface MetricName {

    String HTTP_SERVER_REQUESTS = "http.server.requests";
    String JVM_BUFFER_COUNT = "jvm.buffer.count";
    String JVM_BUFFER_MEMORY_USED = "jvm.buffer.memory.used";
    String JVM_BUFFER_TOTAL_CAPACITY = "jvm.buffer.total.capacity";
    String JVM_CLASSES_LOADED = "jvm.classes.loaded";
    String JVM_CLASSES_UNLOADED = "jvm.classes.unloaded";
    String JVM_GC_LIVE_DATA_SIZE = "jvm.gc.live.data.size";
    String JVM_GC_MAX_DATA_SIZE = "jvm.gc.max.data.size";
    String JVM_GC_MEMORY_ALLOCATED = "jvm.gc.memory.allocated";
    String JVM_GC_MEMORY_PROMOTED = "jvm.gc.memory.promoted";
    String JVM_GC_PAUSE = "jvm.gc.pause";
    String JVM_MEMORY_COMMITTED = "jvm.memory.committed";
    String JVM_MEMORY_MAX = "jvm.memory.max";
    String JVM_MEMORY_USED = "jvm.memory.used";
    String JVM_THREADS_DAEMON = "jvm.threads.daemon";
    String JVM_THREADS_LIVE = "jvm.threads.live";
    String JVM_THREADS_PEAK = "jvm.threads.peak";
    String JVM_THREADS_STATES = "jvm.threads.states";
    String LOGBACK_EVENTS = "logback.events";
    String PROCESS_CPU_USAGE = "process.cpu.usage";
    String PROCESS_FILES_MAX = "process.files.max";
    String PROCESS_FILES_OPEN = "process.files.open";
    String PROCESS_UPTIME = "process.uptime";
    String PROCESS_START_TIME = "process.start.time";
    String SYSTEM_CPU_COUNT = "system.cpu.count";
    String SYSTEM_CPU_USAGE = "system.cpu.usage";
    String SYSTEM_LOAD_AVERAGE_1M = "system.load.average.1m";
    String TOMCAT_SESSIONS_ACTIVE_CURRENT = "tomcat.sessions.active.current";
    String TOMCAT_SESSIONS_ACTIVE_MAX = "tomcat.sessions.active.max";
    String TOMCAT_SESSIONS_ALIVE_MAX = "tomcat.sessions.alive.max";
    String TOMCAT_SESSIONS_CREATED = "tomcat.sessions.created";
    String TOMCAT_SESSIONS_EXPIRED = "tomcat.sessions.expired";
    String TOMCAT_SESSIONS_REJECTED = "tomcat.sessions.rejected";

}
