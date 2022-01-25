package com.yj.monitor.admin.disruptor;

import com.yj.monitor.api.domain.Node;
import com.yj.monitor.api.domain.Method;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:10
 * @Version 1.0
 */
public class MonitorEvent {

    private Node node;
    private Long batchId;
    private Method method;

    public MonitorEvent() {
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }


    public boolean notValid(){
        return this.batchId == null || this.node == null;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
