package com.yj.monitor.core.config;


/**
 * @Author gaolei
 * @Date 2022/1/20 上午11:14
 * @Version 1.0
 */
public class MonitorConfig {
    private AdminConfig adminConfig;
    private Boolean isMonitorOpen;
    private Boolean throwAble;
    private String adminUrl;
    private Boolean retry;
    private Integer retryTime;
    private String localHost;
    private Integer applicationPort;
    private String applicationName;
    private String authToken;
    private String clientId;
    private Integer heartPort;
    private Integer heartInterval;
    private Integer rpcPort;

    public MonitorConfig() {
    }

    public AdminConfig getAdminConfig() {
        return adminConfig;
    }

    public void setAdminConfig(AdminConfig adminConfig) {
        this.adminConfig = adminConfig;
    }

    public Boolean getMonitorOpen() {
        return isMonitorOpen;
    }

    public void setMonitorOpen(Boolean monitorOpen) {
        isMonitorOpen = monitorOpen;
    }

    public Boolean getThrowAble() {
        return throwAble;
    }

    public void setThrowAble(Boolean throwAble) {
        this.throwAble = throwAble;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    public void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }

    public Boolean getRetry() {
        return retry;
    }

    public void setRetry(Boolean retry) {
        this.retry = retry;
    }

    public Integer getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime) {
        this.retryTime = retryTime;
    }

    public String getLocalHost() {
        return localHost;
    }

    public void setLocalHost(String localHost) {
        this.localHost = localHost;
    }

    public Integer getApplicationPort() {
        return applicationPort;
    }

    public void setApplicationPort(Integer applicationPort) {
        this.applicationPort = applicationPort;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getHeartPort() {
        return heartPort;
    }

    public void setHeartPort(Integer heartPort) {
        this.heartPort = heartPort;
    }

    public Integer getHeartInterval() {
        return heartInterval;
    }

    public void setHeartInterval(Integer heartInterval) {
        this.heartInterval = heartInterval;
    }

    public Integer getRpcPort() {
        return rpcPort;
    }

    public void setRpcPort(Integer rpcPort) {
        this.rpcPort = rpcPort;
    }
}
