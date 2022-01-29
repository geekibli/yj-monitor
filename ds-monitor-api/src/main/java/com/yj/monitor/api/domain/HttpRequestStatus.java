package com.yj.monitor.api.domain;

/**
 * @Author gaolei
 * @Date 2022/1/28 下午3:43
 * @Version 1.0
 */
public class HttpRequestStatus {

    private int code;
    private String msg;


    public static HttpRequestStatus SUCCESS = new HttpRequestStatus(200, "Success");
    public static HttpRequestStatus ILLEGAL_ARGUMENT = new HttpRequestStatus(400, "Illegal argument");
    public static HttpRequestStatus UNAUTHORIZED = new HttpRequestStatus(401, "Unauthorized");
    public static HttpRequestStatus FORBIDDEN = new HttpRequestStatus(403, "Forbidden");
    public static HttpRequestStatus NOT_FOUND = new HttpRequestStatus(405, "Not found");
    public static HttpRequestStatus METHOD_NOT_ALLOW = new HttpRequestStatus(405, "Method not allow");
    public static HttpRequestStatus REQUEST_TIMEOUT = new HttpRequestStatus(408, "Request timeout");
    public static HttpRequestStatus INTERNAL_SERVER_ERROR = new HttpRequestStatus(500, "Internal server error");
    public static HttpRequestStatus NOT_IMPLEMENTED = new HttpRequestStatus(501, "Not implemented");
    public static HttpRequestStatus BAD_GATEWAY = new HttpRequestStatus(502, "Bad gateway");
    public static HttpRequestStatus SERVICE_UNAVAILABLE = new HttpRequestStatus(503, "Service unavailable");
    public static HttpRequestStatus GATEWAY_TIMEOUT = new HttpRequestStatus(504, "Gateway timeout");

    public HttpRequestStatus() {
    }

    public HttpRequestStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
