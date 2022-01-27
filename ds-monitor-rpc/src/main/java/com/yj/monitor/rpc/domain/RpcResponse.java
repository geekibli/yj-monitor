package com.yj.monitor.rpc.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/26 下午3:15
 * @Version 1.0
 */
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private Object result;

    public RpcResponse() {
    }

    public RpcResponse(String code, Object result) {
        this.code = code;
        this.result = result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
