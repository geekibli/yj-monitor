package com.yj.monitor.core.config;


import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Author gaolei
 * @Date 2022/1/19 下午4:18
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "yj.monitor",
        ignoreUnknownFields = false)
public class DsMonitorProperties {

    private AdminConfig admin;
    private Boolean isOpen;
    private Boolean isThrowAble;
    private Boolean retry;
    private Integer retryTime;
    private Integer heartPort;
    private String clientId;

    public DsMonitorProperties() {
    }

    public AdminConfig getAdmin() {
        return admin;
    }

    public void setAdmin(AdminConfig admin) {
        this.admin = admin;
    }

    public Boolean getMonitorOpen() {
        return isOpen;
    }

    public void setMonitorOpen(Boolean monitorOpen) {
        isOpen = monitorOpen;
    }

    public Boolean getIsThrowAble() {
        return isThrowAble;
    }

    public void setIsThrowAble(Boolean isThrowAble) {
        this.isThrowAble = isThrowAble;
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

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Boolean getThrowAble() {
        return isThrowAble;
    }

    public void setThrowAble(Boolean throwAble) {
        isThrowAble = throwAble;
    }

    public Integer getHeartPort() {
        return heartPort;
    }

    public void setHeartPort(Integer heartPort) {
        this.heartPort = heartPort;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
