package com.yj.monitor.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author gaolei
 * @Date 2022/1/21 下午2:37
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "yj.monitor",
        ignoreUnknownFields = false)
public class AdminProperties {

    private Integer serverPort;
    private AuthConfig defaultAuth;
    private ThreadPoolConfig threadPool;
    private HeartConfig heart;
    private AdminMonitorConfig.ScheduleConfig memory;
    private AdminMonitorConfig.ScheduleConfig runtime;
    private AdminMonitorConfig.ScheduleConfig gc;
    private AdminMonitorConfig.ScheduleConfig thread;


    static class ScheduleConfig{
        private Long delay;
        private Long initialDelay;

        public Long getDelay() {
            return delay;
        }

        public void setDelay(Long delay) {
            this.delay = delay;
        }

        public Long getInitialDelay() {
            return initialDelay;
        }

        public void setInitialDelay(Long initialDelay) {
            this.initialDelay = initialDelay;
        }
    }


    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public AuthConfig getDefaultAuth() {
        return defaultAuth;
    }

    public void setDefaultAuth(AuthConfig defaultAuth) {
        this.defaultAuth = defaultAuth;
    }

    public ThreadPoolConfig getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPoolConfig threadPool) {
        this.threadPool = threadPool;
    }

    public AdminMonitorConfig.ScheduleConfig getMemory() {
        return memory;
    }

    public void setMemory(AdminMonitorConfig.ScheduleConfig memory) {
        this.memory = memory;
    }

    public AdminMonitorConfig.ScheduleConfig getRuntime() {
        return runtime;
    }

    public void setRuntime(AdminMonitorConfig.ScheduleConfig runtime) {
        this.runtime = runtime;
    }

    public AdminMonitorConfig.ScheduleConfig getGc() {
        return gc;
    }

    public void setGc(AdminMonitorConfig.ScheduleConfig gc) {
        this.gc = gc;
    }

    public AdminMonitorConfig.ScheduleConfig getThread() {
        return thread;
    }

    public void setThread(AdminMonitorConfig.ScheduleConfig thread) {
        this.thread = thread;
    }

    public HeartConfig getHeart() {
        return heart;
    }

    public void setHeart(HeartConfig heart) {
        this.heart = heart;
    }
}
