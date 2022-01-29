package com.yj.monitor.api.constant;


import com.yj.monitor.api.domain.HttpRequestStatus;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午4:59
 * @Version 1.0
 */
public interface RequestStatus {

    HttpRequestStatus SUCCESS = new HttpRequestStatus(200, "Success");
    HttpRequestStatus ILLEGAL_ARGUMENT = new HttpRequestStatus(400, "Illegal argument");
    HttpRequestStatus UNAUTHORIZED = new HttpRequestStatus(401, "Unauthorized");
    HttpRequestStatus FORBIDDEN = new HttpRequestStatus(403, "Forbidden");
    HttpRequestStatus NOT_FOUND = new HttpRequestStatus(405, "Not found");
    HttpRequestStatus METHOD_NOT_ALLOW = new HttpRequestStatus(405, "Method not allow");
    HttpRequestStatus REQUEST_TIMEOUT = new HttpRequestStatus(408, "Request timeout");
    HttpRequestStatus INTERNAL_SERVER_ERROR = new HttpRequestStatus(500, "Internal server error");
    HttpRequestStatus NOT_IMPLEMENTED = new HttpRequestStatus(501, "Not implemented");
    HttpRequestStatus BAD_GATEWAY = new HttpRequestStatus(502, "Bad gateway");
    HttpRequestStatus SERVICE_UNAVAILABLE = new HttpRequestStatus(503, "Service unavailable");
    HttpRequestStatus GATEWAY_TIMEOUT = new HttpRequestStatus(504, "Gateway timeout");

}
