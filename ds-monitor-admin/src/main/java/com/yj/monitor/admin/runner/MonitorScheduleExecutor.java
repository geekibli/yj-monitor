package com.yj.monitor.admin.runner;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author gaolei
 * @Date 2021/6/9 2:06 下午
 * @Version 1.0
 */
public class MonitorScheduleExecutor {

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNameFormat("monitor-schedule-%d")
            .build();

    private static volatile ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = null;

    private MonitorScheduleExecutor() {
    }

    public static ScheduledExecutorService build() {
        if (null == scheduledThreadPoolExecutor) {
            synchronized (MonitorScheduleExecutor.class) {
                if (null == scheduledThreadPoolExecutor) {
                    scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4, THREAD_FACTORY);
                }
            }
        }
        return scheduledThreadPoolExecutor;
    }

}
