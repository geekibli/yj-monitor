package com.yj.monitor.api.rsp;


import com.yj.monitor.api.domain.HttpRequestStatus;


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
        return new Response(HttpRequestStatus.SUCCESS.getCode(), data);
    }

    public static Response error(String reason) {
        return new Response(HttpRequestStatus.INTERNAL_SERVER_ERROR.getCode(), reason);
    }

    public static Response build(int code, String data) {
        return new Response(code, data);
    }

    public static Response build(HttpRequestStatus status) {
        return new Response(status.getCode(), status.getMsg());
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
