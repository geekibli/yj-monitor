package com.yj.monitor.admin.config;


/**
 * @Author gaolei
 * @Date 2022/1/21 下午2:46
 * @Version 1.0
 */
public class AdminMonitorConfig {

    private String applicationName;
    private Integer serverPort;
    private AuthConfig defaultAuth;
    private ThreadPoolConfig threadPool;
    private HeartConfig heart;

    private ScheduleConfig memory;
    private ScheduleConfig runtime;
    private ScheduleConfig gc;
    private ScheduleConfig thread;


    public static class ScheduleConfig{
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


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
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

    public ScheduleConfig getMemory() {
        return memory;
    }

    public void setMemory(ScheduleConfig memory) {
        this.memory = memory;
    }

    public ScheduleConfig getRuntime() {
        return runtime;
    }

    public void setRuntime(ScheduleConfig runtime) {
        this.runtime = runtime;
    }

    public ScheduleConfig getGc() {
        return gc;
    }

    public void setGc(ScheduleConfig gc) {
        this.gc = gc;
    }

    public ScheduleConfig getThread() {
        return thread;
    }

    public void setThread(ScheduleConfig thread) {
        this.thread = thread;
    }

    public HeartConfig getHeart() {
        return heart;
    }

    public void setHeart(HeartConfig heart) {
        this.heart = heart;
    }
}
