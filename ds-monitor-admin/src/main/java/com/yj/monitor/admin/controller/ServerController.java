package com.yj.monitor.admin.controller;

import com.yj.monitor.admin.domain.req.ServerPolylineReqVO;
import com.yj.monitor.admin.service.ServerService;
import com.yj.monitor.api.rsp.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/28 上午9:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/server")
public class ServerController {


    @Resource
    private ServerService serverService;

    @GetMapping(value = "list")
    public Response getServer() {
        return Response.successData(serverService.getServer());
    }


    @PostMapping(value = "polyline")
    public Response polyline(@RequestBody  ServerPolylineReqVO reqVO){
        reqVO.defaultIfNull();
        return Response.successData(serverService.polyline(reqVO));
    }
}
