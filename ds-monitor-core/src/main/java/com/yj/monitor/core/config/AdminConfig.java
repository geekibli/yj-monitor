package com.yj.monitor.core.config;

import com.yj.monitor.api.constant.Defaults;


/**
 * @Author gaolei
 * @Date 2022/1/20 上午11:16
 * @Version 1.0
 */
public class AdminConfig {
    private String host;
    private Integer port;
    private String accessKey;
    private String secret;

    public AdminConfig() {
        this.host = Defaults.HOST;
        this.port = Defaults.PORT;
    }

    public AdminConfig(String host, Integer port) {
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

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
