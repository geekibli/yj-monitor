package com.yj.monitor.admin.runner;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author gaolei
 * @Date 2021/6/9 2:06 下午
 * @Version 1.0
 */
public class MonitorPool {

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNameFormat("rd-spider-thread-%d")
            .build();

    private static volatile ScheduledExecutorService threadPoolExecutor = null;

    private MonitorPool() {
    }

    public static ScheduledExecutorService build() {
        if (null == threadPoolExecutor) {
            synchronized (MonitorPool.class) {
                if (null == threadPoolExecutor) {
                    threadPoolExecutor = Executors.newScheduledThreadPool(3);
                }
            }
        }
        return threadPoolExecutor;
    }

}
