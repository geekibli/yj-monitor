package com.yj.monitor.core.runner;

import cn.hutool.http.HttpUtil;
import com.yj.monitor.api.constant.RemoteAPI;
import com.yj.monitor.core.config.MonitorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author gaolei
 * @Date 2022/1/21 下午5:15
 * @Version 1.0
 */
@Component
public class ContextClosedRunner {

    private final Logger logger = LoggerFactory.getLogger(ContextClosedRunner.class);

    @Resource
    private MonitorConfig monitorConfig;

    @EventListener(classes = {ContextClosedEvent.class})
    public void onMonitorClientClosed(@NonNull ApplicationEvent event) throws InterruptedException {
        if (!monitorConfig.getMonitorOpen()) {
            return;
        }
        Thread thread = new Thread(() -> {
            HashMap<String, Object> param = new HashMap<>();
            param.put("clientId", monitorConfig.getClientId());
            String discardResult = HttpUtil.get(monitorConfig.getAdminUrl() + RemoteAPI.DISCARD_CLIENT, param);
            logger.warn("通知admin清理当前结点 ：{}", discardResult);
        });
        thread.start();
        thread.join();
        logger.info(".....");
    }


    public static void main(String[] args) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("clientId", "yjmonitorcore001");
        System.err.println(HttpUtil.get("http://127.0.0.1:9000" + RemoteAPI.DISCARD_CLIENT, param));
    }
}
