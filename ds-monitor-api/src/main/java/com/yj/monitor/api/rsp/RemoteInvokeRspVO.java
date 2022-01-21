package com.yj.monitor.api.rsp;


import com.yj.monitor.api.constant.RemoteInvokeStatus;


/**
 * @Author gaolei
 * @Date 2022/1/19 下午4:57
 * @Version 1.0
 */
public class RemoteInvokeRspVO {

    private int code;
    private Object data;

    public RemoteInvokeRspVO() {
    }

    public RemoteInvokeRspVO(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static RemoteInvokeRspVO successData(Object data) {
        return new RemoteInvokeRspVO(RemoteInvokeStatus.SUCCESS, data);
    }

    public static RemoteInvokeRspVO failData(String reason) {
        return new RemoteInvokeRspVO(RemoteInvokeStatus.INTERNET_ERROR, reason);
    }

    public static RemoteInvokeRspVO build(int code, String data) {
        return new RemoteInvokeRspVO(code, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
