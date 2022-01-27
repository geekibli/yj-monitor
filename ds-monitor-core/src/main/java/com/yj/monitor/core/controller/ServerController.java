package com.yj.monitor.core.controller;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.core.service.MonitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午9:18
 * @Version 1.0
 */
@RestController
public class ServerController {

    @Resource
    private MonitorService monitorService;

    @GetMapping("server")
    public String getServer(){
        return JSON.toJSONString(monitorService.getServerInfo());
    }


}
