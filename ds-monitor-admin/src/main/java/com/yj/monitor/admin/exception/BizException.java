package com.yj.monitor.admin.exception;

import com.yj.monitor.api.domain.HttpRequestStatus;

/**
 * @Author luhaijun
 * @Description 业务异常，此类异常返回HttpCode都是200
 * @Date 2019/9/25 7:36 PM
 **/

public class BizException extends RuntimeException {

    private final int code;
    private final String msg;

    public BizException(HttpRequestStatus status) {
        super(status.getCode() + ":" + status.getMsg());
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
