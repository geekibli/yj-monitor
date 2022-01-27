package com.yj.monitor.core.service;

import cn.hutool.system.oshi.OshiUtil;
import com.yj.monitor.api.domain.*;
import com.yj.monitor.api.rpc.MonitorApi;
import com.yj.monitor.core.config.MonitorConfig;
import com.yj.monitor.core.handler.JDKManagementHandler;
import com.yj.monitor.core.handler.SpringBootActuatorHandler;
import com.yj.monitor.rpc.anno.Rpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import oshi.hardware.GlobalMemory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author gaolei
 * @Date 2022/1/26 下午12:16
 * @Version 1.0
 * @Desc 监控服务 ： 对 JMX / actuator / oshi 等封装的handler进行包装，堆外提供rpc服务
 */
@Service
@Rpc
public class MonitorService implements MonitorApi {

    private final Logger logger = LoggerFactory.getLogger(MonitorService.class);

    @Resource
    private JDKManagementHandler jdkManagementHandler;
    @Resource
    private MonitorConfig monitorConfig;
    @Resource
    private SpringBootActuatorHandler springBootActuatorHandler;


    @Override
    public Mem getMemInfo() {
        Mem memory = jdkManagementHandler.getMemoryInfo();
        GlobalMemory gm = OshiUtil.getMemory();
        memory.setTotal(gm.getTotal());
        memory.setSwapUsed(gm.getSwapUsed());
        memory.setSwapTotal(gm.getSwapTotal());
        memory.setSwapPagesOut(gm.getSwapPagesOut());
        memory.setSwapPagesIn(gm.getSwapPagesIn());
        memory.setPageSize(gm.getPageSize());
        memory.setAvailable(gm.getAvailable());
        return memory;
    }


    @Override
    public Server getServerInfo() {
        Server server = new Server();
        Map<String, String> operatingSystem = jdkManagementHandler.getOperatingSystemInfo();
        server.setServerType(operatingSystem.get("Name"));
        server.setAvailableProcessors(Integer.valueOf(operatingSystem.get("AvailableProcessors")));
        server.setArch(operatingSystem.get("Arch"));
        server.setSystemLoadAverage(operatingSystem.get("SystemLoadAverage"));

        Map<String, String> systemProperties = jdkManagementHandler.getSystemProperties();
        server.setSunManagementCompile(systemProperties.get("sun.management.compiler"));
        server.setUserCountry(systemProperties.get("user.country"));
        server.setUserName(systemProperties.get("user.name"));
        // "sun.java.command": "com.yj.monitor.core.CoreBootstrap", 进程 TODO
        server.setJvmName(systemProperties.get("java.vm.name"));
        server.setJvmInfo(systemProperties.get("java.vm.info"));

        Disk disk = springBootActuatorHandler.getDisk();
        server.setDiskTotal(disk.getTotal());
        server.setDiskFree(disk.getFree());
        server.setDiskThreshold(disk.getThreshold());
        server.setSystemCpuUsage(springBootActuatorHandler.getSystemCpuUsageVal(monitorConfig.getApplicationPort()));
        return server;
    }


    @Override
    public List<MemoryPartition> getMemoryPartitions() {
        return jdkManagementHandler.getMemoryPartition();
    }


    @Override
    public ClassLoad getClassLoad() {
        return jdkManagementHandler.getClassLoader();
    }


    @Override
    public GarbageCollection getGcInfo() {
        GarbageCollection gc = jdkManagementHandler.getGcInfo();
        int port = monitorConfig.getApplicationPort();
        gc.setLiveDataSize(springBootActuatorHandler.getGcLiveDataSizeVal(port));
        gc.setMaxDataSize(springBootActuatorHandler.getGcMaxDataSizeVal(port));
        gc.setMemoryAllocatedCount(springBootActuatorHandler.getGcMemoryAllocatedVal(port));
        gc.setMemoryPromotedCount(springBootActuatorHandler.getGcMemoryPromotedVal(port));
        Map<String, String> pauseMap = springBootActuatorHandler.getGcPauseMap(port);
        // todo
        gc.setPauseCount(new BigDecimal(pauseMap.get("COUNT")).longValue());
        gc.setPauseMax(new BigDecimal(pauseMap.get("MAX")).longValue());
        if (pauseMap.get("TOTAL_TIME") != null) {
            gc.setPauseTotalTime(new BigDecimal(pauseMap.get("TOTAL_TIME")).doubleValue());
        }
        return gc;
    }


    @Override
    public JmxThread getThread() {
        return jdkManagementHandler.getThreadInfo();
    }
}
