package com.yj.monitor.api.constant;

import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午6:20
 * @Version 1.0
 */
public interface Defaults {

    String HOST = "127.0.0.1";

    Integer PORT = 8080;

    Integer RETRY_TIME = 3;

    String ACCESS_KEY = "admin";

    String SECRET = "123456";

    Integer POOL_SIZE = 3;

    Long HEART_INTERVAL = TimeUnit.SECONDS.toMillis(10);
}
