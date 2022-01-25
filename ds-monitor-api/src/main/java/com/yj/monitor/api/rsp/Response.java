package com.yj.monitor.api.rsp;


import com.yj.monitor.api.constant.RemoteInvokeStatus;


/**
 * @Author gaolei
 * @Date 2022/1/19 下午4:57
 * @Version 1.0
 */
public class Response {

    private int code;
    private Object data;

    public Response() {
    }

    public Response(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Response successData(Object data) {
        return new Response(RemoteInvokeStatus.SUCCESS, data);
    }

    public static Response failData(String reason) {
        return new Response(RemoteInvokeStatus.INTERNET_ERROR, reason);
    }

    public static Response build(int code, String data) {
        return new Response(code, data);
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
