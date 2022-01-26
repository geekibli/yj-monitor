package com.yj.monitor.core.config;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.domain.Token;
import com.yj.monitor.api.util.IpUtil;
import com.yj.monitor.api.util.Rc4Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import oshi.SystemInfo;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午6:14
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(DsMonitorProperties.class)
public class MonitorConfiguration {

    private final Logger logger = LoggerFactory.getLogger(MonitorConfiguration.class);

    @Value(value = "${server.port}")
    private final Integer applicationPort = 8080;

    @Value(value = "${spring.application.name}")
    private String applicationName;

    @Resource
    private DsMonitorProperties monitorProperties;


    @Bean
    public MonitorConfig getConfig() {
        MonitorConfig config = new MonitorConfig();
        AdminConfig adminConfig = new AdminConfig();

        AdminConfig admin = monitorProperties.getAdmin();
        if (null != admin) {
            if (StringUtils.hasLength(admin.getHost())) {
                adminConfig.setHost(admin.getHost());
            }

            if (null != admin.getPort()) {
                adminConfig.setPort(admin.getPort());
            }
        }
        config.setAdminConfig(adminConfig);

        Token token = new Token();
        token.setAccessKey(monitorProperties.getAdmin().getAccessKey());
        token.setSecret(monitorProperties.getAdmin().getSecret());
        config.setAuthToken(Rc4Util.encrypt(JSON.toJSONString(token)));

        config.setClientId(monitorProperties.getClientId());
        config.setMonitorOpen(monitorProperties.getMonitorOpen());
        config.setAdminUrl(IpUtil.ip2Url(adminConfig.getHost(), adminConfig.getPort()));
        config.setThrowAble(monitorProperties.getIsThrowAble());
        config.setRetry(monitorProperties.getRetry());
        config.setRetryTime(monitorProperties.getRetryTime());
        config.setLocalHost(IpUtil.getMyIp());
        config.setApplicationPort(applicationPort);
        config.setApplicationName(applicationName);
        config.setHeartPort(monitorProperties.getHeartPort());
        logger.info(" ==============================  Load client monitor config ============================== \n    {}", JSON.toJSONString(config));
        return config;
    }

    @Bean
    public HttpTraceRepository buildHttpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

    @Bean
    public SystemInfo systemInfo(){
        return new SystemInfo();
    }

}
