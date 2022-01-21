package com.yj.monitor.admin.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/21 下午3:02
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(AdminProperties.class)
public class AdminMonitorConfiguration {

    @Resource
    private AdminProperties adminProperties;

    @Value(value = "${spring.application.name}")
    private String applicationName;

    @Value(value = "${server.port}")
    private Integer serverPort;

    @Bean(name = "adminMonitorConfig")
    public AdminMonitorConfig getConfig() {
        AdminMonitorConfig config = new AdminMonitorConfig();
        config.setMemory(adminProperties.getMemory());
        config.setGc(adminProperties.getGc());
        config.setRuntime(adminProperties.getRuntime());
        config.setThread(adminProperties.getThread());
        config.setServerPort(adminProperties.getServerPort());
        config.setThreadPool(adminProperties.getThreadPool());
        config.setApplicationName(applicationName);
        config.setHeart(adminProperties.getHeart());
        config.setServerPort(serverPort);
        System.err.println("admin config " + JSON.toJSONString(config));
        return config;
    }


}
