package com.yj.monitor.core.runner;

import com.yj.monitor.core.config.MonitorConfig;
import com.yj.monitor.core.service.ClientRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午3:51
 * @Version 1.0
 */
@Component
public class LoadRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(LoadRunner.class);

    public static String localAddress = "";

    @Resource
    private ClientRegisterService clientRegisterService;
    @Resource
    private MonitorConfig monitorConfig;

    @Override
    public void run(String... args) {
        if (!monitorConfig.getMonitorOpen()) {
            logger.warn( "【 " + monitorConfig.getApplicationName() + " 】start with monitor is closed!" );
            return;
        }
        clientRegisterService.register2Admin(0);
    }

}
