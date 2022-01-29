package com.yj.monitor.admin.controller;

import com.yj.monitor.admin.exception.BizException;
import com.yj.monitor.api.domain.HttpRequestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author gaolei
 * @Date 2022/1/29 上午10:13
 * @Version 1.0
 */
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("admin-test")
    public void test() {
        String jksjdk = "sjkldsflkjasddfjdkl";
        logger.info("测试传说道具i哦sad可能 {}" + jksjdk);
        throw new BizException(HttpRequestStatus.BAD_GATEWAY);
    }

}
