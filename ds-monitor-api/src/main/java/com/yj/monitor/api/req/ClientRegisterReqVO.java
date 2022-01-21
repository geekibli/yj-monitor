package com.yj.monitor.api.req;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午12:30
 * @Version 1.0
 */
public class ClientRegisterReqVO {

    private String clientHost;
    private Integer clientPort;
    private String applicationName;
    private String authToken;
    private String clientId;

    public ClientRegisterReqVO() {
    }

    public ClientRegisterReqVO(String clientHost, Integer clientPort, String applicationName) {
        this.clientHost = clientHost;
        this.clientPort = clientPort;
        this.applicationName = applicationName;
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

}
