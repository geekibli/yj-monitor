package com.yj.monitor.admin.cache;


import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.entity.MonitorExceptionLog;
import com.yj.monitor.admin.exception.BizException;
import com.yj.monitor.admin.mapper.MonitorExceptionLogMapper;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.api.domain.HttpRequestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author gaolei
 * @Date 2022/1/28 下午5:57
 * @Version 1.0
 */
@Component
public class ExceptionCache {

    @Resource
    private MonitorExceptionLogMapper monitorExceptionLogMapper;

    // todo
    private final static Integer CACHE_CAPACITY = 1;

    private final Logger logger = LoggerFactory.getLogger(ExceptionCache.class);

    private static ArrayList<MonitorExceptionLog> LOG_LIST = new ArrayList<MonitorExceptionLog>();

    public void saveLog(Exception e) {
        MonitorExecutor.build()
                .execute(() -> {
                    if (isFull()) {
                        flush();
                    }
                    MonitorExceptionLog log = exception2Log(e);
                    addLog(log);
                });

    }

    public synchronized void addLog(MonitorExceptionLog log) {
        LOG_LIST.add(log);
    }

    public boolean isFull() {
        return LOG_LIST.size() == CACHE_CAPACITY;
    }

    public synchronized void flush() {
        for (MonitorExceptionLog log : LOG_LIST) {
            monitorExceptionLogMapper.insertSelective(log);
        }
        LOG_LIST = new ArrayList<>();
    }

    public Integer getCacheSize() {
        return LOG_LIST.size();
    }


    private MonitorExceptionLog exception2Log(Exception e) {
//        String traceId = MDC.getCopyOfContextMap().get("traceId");

        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        if (copyOfContextMap != null && !copyOfContextMap.isEmpty()) {
            System.err.println(copyOfContextMap.get("traceId"));
        }

        String traceId = MDC.get("traceId");

        System.err.println("traceId : {}" + traceId);

        MonitorExceptionLog log = new MonitorExceptionLog();
        log.setTraceId(traceId);

        StackTraceElement[] stackTrace = e.getStackTrace();
        StackTraceElement element = stackTrace[0];
        log.setClassName(element.getClassName());
        log.setMethodName(element.getMethodName());
        log.setMessage(e.getMessage());
        String stack = JSON.toJSONString(stackTrace);
        if (stack.length() > 1000) {
            log.setStackTrace(stack.substring(0, 999));
        } else {
            log.setStackTrace(stack);
        }
        // todo 如何处理client thread存到context map


        return log;
    }


    public static void m1() {
        m2();
    }

    public static void m2() {
        m3();
    }

    public static void m3() {
        throw new BizException(HttpRequestStatus.GATEWAY_TIMEOUT);
    }

}
