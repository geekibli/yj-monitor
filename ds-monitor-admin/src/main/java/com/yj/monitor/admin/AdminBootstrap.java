package com.yj.monitor.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author gaolei
 * @Description 启动引导程序
 * @Date
 */
@SpringBootApplication(scanBasePackages = {"com.yj"})
public class AdminBootstrap extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootstrap.class, args);
    }
}
