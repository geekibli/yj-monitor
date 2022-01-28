package com.yj.monitor.core.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.yj.monitor.api.domain.*;
import com.yj.monitor.api.domain.Runtime;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.lang.management.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午2:22
 * @Version 1.0
 */
@Component
public class JDKManagementHandler {

    public Runtime getRuntimeInfo() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        Runtime rt = new Runtime();
        rt.setClassPath(runtime.getClassPath());
        rt.setBootClassPath(runtime.getBootClassPath());
        rt.setLibraryPath(runtime.getLibraryPath());
        rt.setManagementSpecVersion(runtime.getManagementSpecVersion());
        rt.setName(runtime.getName());
        rt.setSpecName(runtime.getSpecName());
        rt.setSpecVendor(runtime.getSpecVendor());
        rt.setSpecVersion(runtime.getSpecVersion());
        rt.setVmName(runtime.getVmName());
        rt.setVmVendor(runtime.getVmVendor());
        rt.setVmVersion(runtime.getVmVersion());
        rt.setInputArguments(JSON.toJSONString(runtime.getInputArguments()));
        rt.setStartTime(runtime.getStartTime());
        rt.setUpTime(runtime.getUptime());
        return rt;
    }

    public Map<String, String> getSystemProperties() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        return runtime.getSystemProperties();
    }


    public Map<String, String> getOperatingSystemInfo() {
        OperatingSystemMXBean osb = ManagementFactory.getOperatingSystemMXBean();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("Name", osb.getName());
        map.put("Arch", osb.getArch());
        map.put("AvailableProcessors", osb.getAvailableProcessors() + "");
        map.put("Version", osb.getVersion());
        map.put("SystemLoadAverage", osb.getSystemLoadAverage() + "");
        return map;
    }

    public Mem getMemoryInfo() {
        MemoryMXBean mxb = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = mxb.getHeapMemoryUsage();
        Mem mem = new Mem();
        mem.setHeapCommitted(heap.getCommitted());
        mem.setHeapInit(heap.getInit());
        mem.setHeapMax(heap.getMax());
        mem.setHeapUsed(heap.getUsed());
        MemoryUsage nonHeap = mxb.getNonHeapMemoryUsage();
        mem.setNonHeapCommitted(nonHeap.getCommitted());
        mem.setNonHeapInit(nonHeap.getInit());
        mem.setNonHeapMax(nonHeap.getMax());
        mem.setNonHeapUsed(nonHeap.getUsed());

//        com.sun.management.OperatingSystemMXBean osmxb = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//        map.put("freePhysicalMemorySize", osmxb.getFreePhysicalMemorySize());
//        map.put("totalPhysicalMemorySize", osmxb.getTotalPhysicalMemorySize());
//        map.put("freeSwapSpaceSize", osmxb.getFreeSwapSpaceSize());
//        map.put("totalSwapSpaceSize", osmxb.getTotalSwapSpaceSize());
        return mem;
    }

    public JmxThread getThreadInfo() {
        ThreadMXBean thread = ManagementFactory.getThreadMXBean();
        JmxThread jt = new JmxThread();
        jt.setTotalStartedThreadCount(thread.getTotalStartedThreadCount());
        jt.setThreadCount(thread.getThreadCount());
        if (thread.findDeadlockedThreads() != null && thread.findDeadlockedThreads().length > 0) {
            jt.setDeadLockedThreads(JSON.toJSONString(thread.findDeadlockedThreads()));
        }
        jt.setCurrentThreadCpuTime(thread.getCurrentThreadCpuTime());
        jt.setCurrentThreadUserTime(thread.getCurrentThreadUserTime());
        jt.setDaemonThreadCount(thread.getDaemonThreadCount());
        jt.setPeakThreadCount(thread.getPeakThreadCount());
        jt.setCurrentThreadCpuTimeSupported(thread.isCurrentThreadCpuTimeSupported());
        jt.setObjectMonitorUsageSupported(thread.isObjectMonitorUsageSupported());
        jt.setSynchronizerUsageSupported(thread.isSynchronizerUsageSupported());
        jt.setThreadContentionMonitoringEnabled(thread.isThreadContentionMonitoringEnabled());
        jt.setThreadContentionMonitoringSupported(thread.isThreadContentionMonitoringSupported());
        jt.setThreadCpuTimeEnabled(thread.isThreadCpuTimeEnabled());
        jt.setThreadCpuTimeSupported(thread.isThreadCpuTimeSupported());
        return jt;
    }

    public ClassLoad getClassLoader() {
        ClassLoadingMXBean classLoading = ManagementFactory.getClassLoadingMXBean();
        ClassLoad cl = new ClassLoad();
        cl.setLoaderClassCount(classLoading.getUnloadedClassCount());
        cl.setTotalLoaderClassCount(classLoading.getTotalLoadedClassCount());
        cl.setUnloadedClassCount(classLoading.getUnloadedClassCount());
        cl.setVerbose(classLoading.isVerbose());
        return cl;
    }


    public GarbageCollection getGcInfo() {
        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        Map<String, GarbageCollectorMXBean> mxBeanMap = gcList.stream().collect(Collectors.toMap(GarbageCollectorMXBean::getName, Function.identity()));
        GarbageCollectorMXBean ms = mxBeanMap.get("PS MarkSweep");
        GarbageCollection gc = new GarbageCollection();
        if (ms != null) {
            gc.setPsMarksweepCollectionCount(ms.getCollectionCount());
            gc.setPsMarksweepCollectionTime(ms.getCollectionTime());
        }

        GarbageCollectorMXBean sc = mxBeanMap.get("PS Scavenge");
        if (sc != null) {
            gc.setPsScavengeCollectionCount(sc.getCollectionCount());
            gc.setPsScavengeCollectionTime(sc.getCollectionTime());
        }
        return gc;
    }


    public Map<String, String> getCompilation() {
        CompilationMXBean compilation = ManagementFactory.getCompilationMXBean();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Name", compilation.getName());
        map.put("TotalCompilationTime", compilation.getTotalCompilationTime() + "");
        map.put("isCompilationTimeMonitoringSupported", compilation.isCompilationTimeMonitoringSupported() + "");
        return map;
    }


    public List<MemoryPartition> getMemoryPartition() { // 获取所有内存池MXBean列表，并遍历
        List<MemoryPoolMXBean> mxBeans = ManagementFactory.getMemoryPoolMXBeans();
        if (CollectionUtils.isEmpty(mxBeans)) {
            return new ArrayList<>();
        }

        return mxBeans.stream()
                .map(mxBean -> {
                    MemoryPartition partition = new MemoryPartition();
                    // 内存分区名
                    partition.setName(mxBean.getName());
                    // 内存管理器名称
                    String[] memoryManagerNames = mxBean.getMemoryManagerNames();
                    partition.setManages(joinString(memoryManagerNames));
                    // 内存分区类型
                    partition.setPartitionType(mxBean.getType().toString());
                    // 内存使用情况
                    MemoryUsage usage = mxBean.getUsage();
                    if (null != usage) {
                        partition.setUsageCommitted(usage.getCommitted());
                        partition.setUsageInit(usage.getInit());
                        partition.setUsageMax(usage.getCommitted());
                        partition.setUsageUsed(usage.getUsed());
                    }

                    // 内存使用峰值情况
                    MemoryUsage peakUsage = mxBean.getPeakUsage();
                    if (null != peakUsage) {
                        partition.setPeakUsageCommitted(peakUsage.getCommitted());
                        partition.setPeakUsageInit(peakUsage.getInit());
                        partition.setPeakUsageMax(peakUsage.getMax());
                        partition.setPeakUsageUsed(peakUsage.getUsed());
                    }

                    // 内存回收
                    MemoryUsage collectionUsage = mxBean.getCollectionUsage();
                    if (null != collectionUsage) {
                        partition.setCollectionUsageCommitted(collectionUsage.getCommitted());
                        partition.setCollectionUsageInit(collectionUsage.getInit());
                        partition.setCollectionUsageMax(collectionUsage.getMax());
                        partition.setCollectionUsageUsed(collectionUsage.getUsed());
                    }

                    return partition;
                }).collect(Collectors.toList());
    }


    private String joinString(String[] vars) {
        if (1 == vars.length) {
            return vars[0];
        }
        StringJoiner joiner = new StringJoiner(",");
        for (String var : vars) {
            joiner.add(var);
        }
        return joiner.toString();
    }

}
