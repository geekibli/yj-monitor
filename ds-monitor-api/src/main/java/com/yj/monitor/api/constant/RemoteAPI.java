package com.yj.monitor.api.constant;

import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午6:41
 * @Version 1.0
 */
public interface RemoteAPI {

    /**
     * 注册接口
     */
    String REGISTER_2_ADMIN = "/register";
    String REGISTER_2_ADMIN_RSP = "hello, welcome!";

    /**
     * 心跳检测
     */
    String HEART_BEAT_MSG = "heart beat!";
    String HEART_BEAT_OK = "ok";
    long HEART_INTERVAL = TimeUnit.SECONDS.toMillis(3);

    /**
     * JDK JMX监控
     */
    String MONITOR_PULL = "/monitor";

    /**
     * 客户端注销
     */
    String DISCARD_CLIENT = "/discard";
    String DISCARD_CLIENT_RSP = "Discard client success!";

    /**
     * Springboot actuator监控
     */
    String ACTUATOR_METRICS = "/actuator-metrics";

}
