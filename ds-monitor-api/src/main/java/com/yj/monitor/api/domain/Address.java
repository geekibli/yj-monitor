package com.yj.monitor.api.domain;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午4:18
 * @Version 1.0
 */
public class Address {

    private String host;
    private int port;

    public Address(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
