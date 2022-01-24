package com.yj.monitor.api.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午6:41
 * @Version 1.0
 */
public interface RemoteAPI {

    String register2admin = "/register";
    String register2adminRsp = "hello, welcome!";

    String HEART_BEAT_MSG = "heart beat!";
    String HEART_BEAT_OK = "ok";
    long HEART_INTERVAL = TimeUnit.SECONDS.toMillis(3);

    String MONITOR_PULL = "/monitor";

    String DISCARD_CLIENT = "/discard";
    String DISCARD_CLIENT_RSP = "Discard client success!";

}
