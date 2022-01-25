package com.yj.monitor.admin.disruptor;

import com.yj.monitor.api.domain.Client;
import com.yj.monitor.api.domain.Method;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:10
 * @Version 1.0
 */
public class MonitorEvent {

    private Client client;
    private Long batchId;
    private Method method;

    public MonitorEvent() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }


    public boolean notValid(){
        return this.batchId == null || this.client == null;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
