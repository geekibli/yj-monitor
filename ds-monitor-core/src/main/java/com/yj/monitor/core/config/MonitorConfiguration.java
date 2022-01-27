package com.yj.monitor.core.config;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.constant.Defaults;
import com.yj.monitor.api.domain.Token;
import com.yj.monitor.api.util.IpUtil;
import com.yj.monitor.api.util.Rc4Util;
import com.yj.monitor.rpc.handler.ObjectDecoder;
import com.yj.monitor.rpc.server.RpcServer;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;
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
@Import(value = {RpcServer.class})
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
        Assert.notNull(admin, "admin config lose!");
        Assert.hasLength(admin.getHost(), "admin host config lose!");
        adminConfig.setHost(admin.getHost());
        Assert.notNull(admin.getHost(), "admin port config lose!");
        adminConfig.setPort(admin.getPort());
        config.setAdminConfig(adminConfig);
        config.setAdminUrl(IpUtil.ip2Url(adminConfig.getHost(), adminConfig.getPort()));
        String localIp = IpUtil.getMyIp();
        config.setLocalHost(localIp);

        Token token = new Token();
        String accessKey = monitorProperties.getAdmin().getAccessKey();
        token.setAccessKey(StringUtils.hasLength(accessKey) ? accessKey : Defaults.ACCESS_KEY);
        String secret = monitorProperties.getAdmin().getSecret();
        token.setSecret(StringUtils.hasLength(secret) ? secret : Defaults.SECRET);
        config.setAuthToken(Rc4Util.encrypt(JSON.toJSONString(token)));


        String configClientId = monitorProperties.getClientId();
        if (!StringUtils.hasLength(configClientId)) {
            configClientId = applicationName + "-" + (localIp + applicationPort).hashCode();
        }
        config.setClientId(configClientId);

        config.setMonitorOpen(monitorProperties.getMonitorOpen() == null ? false : monitorProperties.getMonitorOpen());
        config.setThrowAble(monitorProperties.getIsThrowAble());

        Boolean retry = monitorProperties.getRetry();
        config.setRetry(retry == null ? true : retry);
        Integer retryTime = monitorProperties.getRetryTime();
        config.setRetryTime(retryTime == null ? Defaults.RETRY_TIME : retryTime);

        config.setApplicationPort(applicationPort);
        config.setApplicationName(applicationName);
        config.setHeartPort(monitorProperties.getHeartPort());

        Integer heartInterval = monitorProperties.getHeartInterval();
        if (heartInterval == null) {
            heartInterval = Defaults.HEART_INTERVAL.intValue();
        }
        config.setHeartInterval(heartInterval);
        logger.info(" ==============================  Load client monitor config ==============================     {}", JSON.toJSONString(config));
        return config;
    }

    @Bean
    public HttpTraceRepository buildHttpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

    @Bean
    public SystemInfo systemInfo() {
        return new SystemInfo();
    }

    @Bean
    public StringDecoder getStringDecoder() {
        return new StringDecoder();
    }

    @Bean
    public StringEncoder getStringEncoder() {
        return new StringEncoder();
    }

    @Bean
    public ObjectEncoder getObjectEncoder() {
        return new ObjectEncoder();
    }

    @Bean
    public ObjectDecoder getObjectDecoder() {
        return new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null));
    }
}
