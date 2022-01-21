package com.yj.monitor.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @Author gaolei
 * @Date 2022/1/19 下午6:12
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.yj"})
public class CoreBootstrap extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CoreBootstrap.class, args);
    }
}
