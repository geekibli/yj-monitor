package com.yj.monitor.admin.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.domain.RegisterCenter;
import com.yj.monitor.admin.domain.req.ApplicationPageListReqVO;
import com.yj.monitor.admin.handler.AuthHandler;
import com.yj.monitor.admin.service.RegisterService;
import com.yj.monitor.api.constant.RemoteAPI;
import com.yj.monitor.api.domain.Node;
import com.yj.monitor.api.rsp.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午7:11
 * @Version 1.0
 */
@RestController
public class RegisterController {

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Resource
    private RegisterService registerService;
    @Resource
    private AuthHandler authHandler;

    @RequestMapping("/register")
    public String handleRegister(@RequestBody Node node) {
        logger.warn("Client coming for : {}", JSON.toJSONString(node));

        Assert.state(StringUtils.isNotBlank(node.getClientId()), "Client must not null!");
        Assert.state(StringUtils.isNotBlank(node.getAuthToken()), "AuthToken must not null!");
        Assert.state(authHandler.validToken(node.getAuthToken()), "Auth fail!");
        Assert.state(!RegisterCenter.isOnline(node.getClientId()), "Dup clientId!");

        registerService.register(node);
        return RemoteAPI.REGISTER_2_ADMIN_RSP;
    }

    @RequestMapping("/online/nodes")
    public String getOnlineList() {
        return JSON.toJSONString(RegisterCenter.onlineClient());
    }

    /**
     * 分页查询应用列表
     *
     * @param reqVO 请求参数
     * @return 应用列表
     */
    @RequestMapping("/app-list")
    public Response pageList(@RequestBody @Valid @NotNull ApplicationPageListReqVO reqVO) {
        reqVO.pageDefaultIfNull();
        return Response.successData(registerService.pageList(reqVO));
    }

}
