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

    private ScheduleConfig pullTask;

    public static class ScheduleConfig{
        private Boolean open;
        private Long delay;
        private Long initialDelay;

        public Boolean getOpen() {
            return open;
        }

        public void setOpen(Boolean open) {
            this.open = open;
        }

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

    public HeartConfig getHeart() {
        return heart;
    }

    public void setHeart(HeartConfig heart) {
        this.heart = heart;
    }

    public ScheduleConfig getPullTask() {
        return pullTask;
    }

    public void setPullTask(ScheduleConfig pullTask) {
        this.pullTask = pullTask;
    }
}
