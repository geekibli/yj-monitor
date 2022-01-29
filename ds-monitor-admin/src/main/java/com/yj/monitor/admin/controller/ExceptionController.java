package com.yj.monitor.admin.controller;

import com.yj.monitor.admin.domain.req.ExceptionPageListReqVO;
import com.yj.monitor.admin.service.ExceptionService;
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
 * @Date 2022/1/29 下午2:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/exception")
public class ExceptionController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @Resource
    private ExceptionService exceptionService;

    @PostMapping(value = "pageList")
    public Response pageList(@RequestBody ExceptionPageListReqVO reqVO) {
        reqVO.pageDefaultIfNull();
        return Response.successData(exceptionService.pageList(reqVO));
    }





}
