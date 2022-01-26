package com.yj.monitor.core.service;

import com.yj.monitor.api.domain.Disk;
import com.yj.monitor.api.domain.Server;
import com.yj.monitor.core.config.MonitorConfig;
import com.yj.monitor.core.handler.JDKManagementHandler;
import com.yj.monitor.core.handler.SpringBootActuatorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午8:09
 * @Version 1.0
 */
@Service
public class ServerService {

    private final Logger logger = LoggerFactory.getLogger(ServerService.class);

    @Resource
    private MonitorConfig monitorConfig;
    @Resource
    private JDKManagementHandler jdkManagementHandler;
    @Resource
    private SpringBootActuatorHandler springBootActuatorHandler;

    public Server getServer() {
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
        server.setSystemCpuUsage(springBootActuatorHandler.getSystemCpuUsage(monitorConfig.getApplicationPort()));

        // TODO 内存
        // https://www.cnblogs.com/rvs-2016/p/11169894.html
        return server;
    }


}
