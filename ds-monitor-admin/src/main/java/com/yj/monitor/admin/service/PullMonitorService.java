package com.yj.monitor.admin.service;

import com.yj.monitor.admin.config.AdminMonitorConfig;
import com.yj.monitor.admin.doman.ClientContainer;
import com.yj.monitor.admin.runner.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午5:30
 * @Version 1.0
 */
@Service
public class PullMonitorService {

    @Resource
    private AdminMonitorConfig adminMonitorConfig;

    public synchronized void execute() {
        pullMemory();
        pullThread();
        pullRuntime();
        pullGc();
    }

    public void pullMemory() {
        AdminMonitorConfig.ScheduleConfig memory = adminMonitorConfig.getMemory();
        MonitorPool.build().scheduleWithFixedDelay(new PullMemoryTask(), memory.getInitialDelay(), memory.getDelay(), TimeUnit.SECONDS);
    }

    public void pullThread() {
        AdminMonitorConfig.ScheduleConfig thread = adminMonitorConfig.getThread();
        MonitorPool.build().scheduleWithFixedDelay(new PullThreadTask(ClientContainer.onlineClient()), thread.getInitialDelay(), thread.getDelay(), TimeUnit.SECONDS);
    }

    public void pullRuntime() {
        AdminMonitorConfig.ScheduleConfig runtime = adminMonitorConfig.getRuntime();
        MonitorPool.build().scheduleWithFixedDelay(new PullRuntimeTask(ClientContainer.onlineClient()), runtime.getInitialDelay(), runtime.getDelay(), TimeUnit.SECONDS);
    }

    public void pullGc() {
        AdminMonitorConfig.ScheduleConfig gc = adminMonitorConfig.getGc();
        MonitorPool.build().scheduleWithFixedDelay(new PullGcTask(ClientContainer.onlineClient()), gc.getInitialDelay(), gc.getDelay(), TimeUnit.SECONDS);
    }

}
