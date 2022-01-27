package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午12:15
 * @Version 1.0
 */
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 基本信息
     */
    private String clientHost;
    private Integer clientPort;
    private String clientId;
    private String address;
    private String applicationName;
    private String systemType;

    /**
     * 接口信息
     */
    private String rpcAddress;
    private String monitorUrl;
    private String actuatorMetricsUrl;

    /**
     * 认证权限信息
     */
    String authToken;


    public Node() {
    }

    public Node(String clientHost, Integer clientPort) {
        this.clientHost = clientHost;
        this.clientPort = clientPort;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public Integer getClientPort() {
        return clientPort;
    }

    public void setClientPort(Integer clientPort) {
        this.clientPort = clientPort;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getMonitorUrl() {
        return monitorUrl;
    }

    public void setMonitorUrl(String monitorUrl) {
        this.monitorUrl = monitorUrl;
    }

    public String getActuatorMetricsUrl() {
        return actuatorMetricsUrl;
    }

    public void setActuatorMetricsUrl(String actuatorMetricsUrl) {
        this.actuatorMetricsUrl = actuatorMetricsUrl;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getRpcAddress() {
        return rpcAddress;
    }

    public void setRpcAddress(String rpcAddress) {
        this.rpcAddress = rpcAddress;
    }
}
