package com.yj.monitor.admin.domain.req;

import com.yj.monitor.admin.domain.BasePageReq;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午2:21
 * @Version 1.0
 */
public class ExceptionPageListReqVO extends BasePageReq {
    private String className;
    private String methodName;

    public ExceptionPageListReqVO() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
