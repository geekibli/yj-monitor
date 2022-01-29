package com.yj.monitor.admin.controller;

import com.yj.monitor.admin.domain.req.ThreadpolylineReqVO;
import com.yj.monitor.admin.service.ThreadService;
import com.yj.monitor.api.rsp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午3:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/thread")
public class ThreadController {


    private final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @Resource
    private ThreadService threadService;

    @PostMapping("polyline")
    public Response polyline(@RequestBody ThreadpolylineReqVO reqVO) {
        reqVO.defaultIfNull();
        return Response.successData(threadService.polyline(reqVO));
    }


}
