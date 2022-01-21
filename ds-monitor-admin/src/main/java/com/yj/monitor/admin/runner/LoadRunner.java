package com.yj.monitor.admin.runner;

import com.yj.monitor.admin.service.HeartBeatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @Author gaolei
 * @Date 2022/1/20 下午4:22
 * @Version 1.0
 */
@Component
public class LoadRunner implements CommandLineRunner {

    @Resource
    private HeartBeatService heartBeatService;

    @Override
    public void run(String... args) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        heartBeatService.heartListen();
    }
}
