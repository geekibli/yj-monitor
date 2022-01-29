package com.yj.monitor.admin.exception;


public class UriResourceNotFoundException extends RuntimeException {

    public UriResourceNotFoundException() {
        super();
    }

    public UriResourceNotFoundException(String message) {
        super(message);
    }

    public UriResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
