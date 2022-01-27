package com.yj.monitor.admin.config;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(AdminMonitorConfiguration.class);

    @Resource
    private AdminProperties adminProperties;

    @Value(value = "${spring.application.name}")
    private String applicationName;

    @Value(value = "${server.port}")
    private Integer serverPort;

    @Bean(name = "adminMonitorConfig")
    public AdminMonitorConfig getConfig() {
        AdminMonitorConfig config = new AdminMonitorConfig();
        config.setServerPort(adminProperties.getServerPort());
        config.setThreadPool(adminProperties.getThreadPool());
        config.setApplicationName(applicationName);
        config.setHeart(adminProperties.getHeart());
        config.setServerPort(serverPort);
        config.setPullTask(adminProperties.getPullTask());
        logger.info("admin config " + JSON.toJSONString(config));
        return config;
    }


    @Bean
    public StringDecoder getStringDecoder(){
        return new StringDecoder();
    }

    @Bean
    public StringEncoder getStringEncoder(){
        return new StringEncoder();
    }

}
