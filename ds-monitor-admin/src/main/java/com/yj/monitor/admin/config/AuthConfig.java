package com.yj.monitor.admin.config;

/**
 * @Author gaolei
 * @Date 2022/1/21 下午2:39
 * @Version 1.0
 */
public class AuthConfig {

    private String accessKey;
    private String secret;

    public AuthConfig(String accessKey, String secret) {
        this.accessKey = accessKey;
        this.secret = secret;
    }

    public AuthConfig() {
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
