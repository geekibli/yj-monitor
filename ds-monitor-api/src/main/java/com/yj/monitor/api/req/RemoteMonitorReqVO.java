package com.yj.monitor.api.req;


/**
 * @Author gaolei
 * @Date 2022/1/19 下午4:56
 * @Version 1.0
 */
public class RemoteMonitorReqVO {

    private String className;
    private String method;
    private Object[] params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public RemoteMonitorReqVO(String className, String method, Object[] params) {
        this.className = className;
        this.method = method;
        this.params = params;
    }

    public RemoteMonitorReqVO() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
