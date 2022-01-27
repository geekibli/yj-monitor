package com.yj.monitor.api.rpc;

import com.yj.monitor.api.domain.*;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/26 下午5:08
 * @Version 1.0
 */
public interface MonitorApi {

    Mem getMemInfo();

    Server getServerInfo();

    List<MemoryPartition> getMemoryPartitions();

    ClassLoad getClassLoad();

    GarbageCollection getGcInfo();

    JmxThread getThread();

}
