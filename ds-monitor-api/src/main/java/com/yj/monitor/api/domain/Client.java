package com.yj.monitor.api.domain;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午12:15
 * @Version 1.0
 */
public class Client {

    private String host;
    private Integer port;
    private String monitorUrl;
    private String actuatorMetricsUrl;
    private String clientId;
    // /127.0.0.1:60571
    private String address;


    public Client() {
    }

    public Client(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMonitorUrl() {
        return monitorUrl;
    }

    public void setMonitorUrl(String monitorUrl) {
        this.monitorUrl = monitorUrl;
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

    public String getActuatorMetricsUrl() {
        return actuatorMetricsUrl;
    }

    public void setActuatorMetricsUrl(String actuatorMetricsUrl) {
        this.actuatorMetricsUrl = actuatorMetricsUrl;
    }
}
