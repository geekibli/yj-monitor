package com.yj.monitor.admin.exception;

/**
 * @Author luhaijun
 * @Description 无权限
 * @Date 2019/9/29 11:48 AM
 **/
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
