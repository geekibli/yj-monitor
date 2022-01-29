package com.yj.monitor.admin.domain.req;

import com.yj.monitor.admin.domain.BaseAggregationReq;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午4:17
 * @Version 1.0
 */
public class ServerPolylineReqVO extends BaseAggregationReq {
    public ServerPolylineReqVO(){}

    public String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
