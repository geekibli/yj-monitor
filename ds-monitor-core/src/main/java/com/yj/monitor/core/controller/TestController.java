package com.yj.monitor.core.controller;

import com.yj.monitor.api.util.PidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author gaolei
 * @Date 2022/1/21 上午10:41
 * @Version 1.0
 */
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("test")
    public void test(){
        logger.warn("PidUtils.currentPid() : {}" , PidUtils.currentPid());
    }
}
