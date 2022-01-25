package com.yj.monitor.admin.domain.req;

import com.yj.monitor.admin.domain.BasePageReq;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午5:49
 * @Version 1.0
 */
public class ApplicationPageListReqVO extends BasePageReq {
    private String clientId;
    private String applicationName;

    public ApplicationPageListReqVO() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
