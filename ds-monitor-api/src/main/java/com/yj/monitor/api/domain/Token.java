package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/21 上午11:59
 * @Version 1.0
 */
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accessKey;
    private String secret;

    public Token() {
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
