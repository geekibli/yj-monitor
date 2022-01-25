package com.yj.monitor.admin.runner;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午6:46
 * @Version 1.0
 */
public class MonitorExecutor {

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("monitor-%d").build();
    private volatile static ThreadPoolExecutor executor = null;

    private MonitorExecutor() {
    }

    public static ThreadPoolExecutor build() {
        if (null == executor) {
            synchronized (MonitorExecutor.class) {
                executor = new ThreadPoolExecutor(4,
                        8,
                        0L,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(128),
                        THREAD_FACTORY,
                        new ThreadPoolExecutor.AbortPolicy());
            }
        }
        return executor;
    }
}
