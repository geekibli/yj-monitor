package com.yj.monitor.core.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.lang.management.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午2:22
 * @Version 1.0
 */
public class MonitorHandler {

    public static Map<String, String> getRuntimeInfo() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ClassPath", runtime.getClassPath());
        map.put("BootClassPath", runtime.getBootClassPath());
        map.put("LibraryPath", runtime.getLibraryPath());
        map.put("ManagementSpecVersion", runtime.getManagementSpecVersion());
        map.put("Name", runtime.getName());
        map.put("SpecName", runtime.getSpecName());
        map.put("SpecVendor", runtime.getSpecVendor());
        map.put("SpecVersion", runtime.getSpecVersion());
        map.put("VmName", runtime.getVmName());
        map.put("VmVendor", runtime.getVmVendor());
        map.put("VmVersion", runtime.getVmVersion());
        map.put("InputArguments", JSON.toJSONString(runtime.getInputArguments()));
        map.put("StartTime", runtime.getStartTime() + "");
//        map.put("SystemProperties", JSON.toJSONString(runtime.getSystemProperties()));
        map.put("Uptime", runtime.getUptime() + "");
        return map;
    }

    public static Map<String, String> getSystemProperties() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        return runtime.getSystemProperties();
    }


    public static Map<String, String> getOperatingSystemInfo() {
        OperatingSystemMXBean osb = ManagementFactory.getOperatingSystemMXBean();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("Name", osb.getName());
        map.put("Arch", osb.getArch());
        map.put("AvailableProcessors", osb.getAvailableProcessors() + "");
        map.put("Version", osb.getVersion());
        map.put("SystemLoadAverage", osb.getSystemLoadAverage() + "");
        return map;
    }

    public static Map<String, String> getMemoryInfo() {
        MemoryMXBean mxb = ManagementFactory.getMemoryMXBean();
        HashMap<String, String> map = new HashMap<String, String>();
        MemoryUsage heap = mxb.getHeapMemoryUsage();
        map.put("heap.max", formatSize(heap.getMax()));
        map.put("heap.committed", formatSize(heap.getCommitted()));
        map.put("heap.used", formatSize(heap.getUsed()));
        map.put("heap.init", formatSize(heap.getInit()));

        MemoryUsage nonHeap = mxb.getNonHeapMemoryUsage();
        map.put("nonHeap.init", formatSize(nonHeap.getInit()));
        map.put("nonHeap.used", formatSize(nonHeap.getUsed()));
        map.put("nonHeap.committed", formatSize(nonHeap.getCommitted()));
        map.put("nonHeap.max", formatSize(nonHeap.getMax()));
        return map;
    }

    public static Map<String, String> getThreadInfo() {
        ThreadMXBean thread = ManagementFactory.getThreadMXBean();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("TotalStartedThreadCount", thread.getTotalStartedThreadCount() + "");
        map.put("ThreadCount", thread.getThreadCount() + "");
        map.put("DeadlockedThreads", JSON.toJSONString(thread.findDeadlockedThreads()));
        map.put("CurrentThreadCpuTime", thread.getCurrentThreadCpuTime() + "");
        map.put("CurrentThreadUserTime", thread.getCurrentThreadUserTime() + "");
        map.put("DaemonThreadCount", thread.getDaemonThreadCount() + "");
        map.put("PeakThreadCount", thread.getPeakThreadCount() + "");
        map.put("isCurrentThreadCpuTimeSupported", thread.isCurrentThreadCpuTimeSupported() + "");
        map.put("isObjectMonitorUsageSupported", thread.isObjectMonitorUsageSupported() + "");
        map.put("isSynchronizerUsageSupported", thread.isSynchronizerUsageSupported() + "");
        map.put("isThreadContentionMonitoringEnabled", thread.isThreadContentionMonitoringEnabled() + "");
        map.put("isThreadContentionMonitoringSupported", thread.isThreadContentionMonitoringSupported() + "");
        map.put("isThreadCpuTimeEnabled", thread.isThreadCpuTimeEnabled() + "");
        map.put("isThreadCpuTimeSupported", thread.isThreadCpuTimeSupported() + "");
        return map;
    }

    public static Map<String, String> getClassLoader() {
        ClassLoadingMXBean classLoading = ManagementFactory.getClassLoadingMXBean();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("LoadedClassCount", classLoading.getLoadedClassCount() + "");
        map.put("TotalLoadedClassCount", classLoading.getTotalLoadedClassCount() + "");
        map.put("UnloadedClassCount", classLoading.getUnloadedClassCount() + "");
        map.put("Verbose", classLoading.isVerbose() + "");
        return map;
    }

    public static Map<String, Map<String, Long>> getGcInfo() {
        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        HashMap<String, Map<String, Long>> gcMap = new HashMap<String, Map<String, Long>>();
        for (GarbageCollectorMXBean gc : gcList) {
            HashMap<String, Long> map = new HashMap<String, Long>();
            map.put("CollectionCount", gc.getCollectionCount());
            map.put("CollectionTime", gc.getCollectionTime());
            gcMap.put(gc.getName(), map);
        }
        return gcMap;
    }


    public static Map<String, String> getCompilation() {
        CompilationMXBean compilation = ManagementFactory.getCompilationMXBean();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Name", compilation.getName());
        map.put("TotalCompilationTime", compilation.getTotalCompilationTime() + "");
        map.put("isCompilationTimeMonitoringSupported", compilation.isCompilationTimeMonitoringSupported() + "");
        return map;
    }


    private static String formatSize(long size) {
        String hrSize;
        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }
        return hrSize;
    }
}
