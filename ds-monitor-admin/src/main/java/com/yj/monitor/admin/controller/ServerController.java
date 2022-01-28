package com.yj.monitor.admin.controller;

import com.yj.monitor.admin.service.ServerService;
import com.yj.monitor.api.rsp.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/28 上午9:42
 * @Version 1.0
 */
@RestController
@RequestMapping("server")
public class ServerController {


    @Resource
    private ServerService serverService;

    @GetMapping
    @RequestMapping("list")
    public Response getServer() {
        return Response.successData(serverService.getServer());
    }


}
