package com.yj.monitor.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @Author gaolei
 * @Date 2022/1/20 下午3:43
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.yj"})
public class DemoBootstarp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DemoBootstarp.class, args);
    }
}
