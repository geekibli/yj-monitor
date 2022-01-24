package com.yj.monitor.admin.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.domain.ClientContainer;
import com.yj.monitor.admin.handler.AuthHandler;
import com.yj.monitor.admin.service.RegisterService;
import com.yj.monitor.api.constant.RemoteAPI;
import com.yj.monitor.api.req.ClientRegisterReqVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public String handleRegister(@RequestBody ClientRegisterReqVO reqVo) {
        logger.warn("Client coming for : {}", JSON.toJSONString(reqVo));

        Assert.state(StringUtils.isNotBlank(reqVo.getClientId()), "Client must not null!");
        Assert.state(StringUtils.isNotBlank(reqVo.getAuthToken()), "AuthToken must not null!");
        Assert.state(authHandler.validToken(reqVo.getAuthToken()), "Auth fail!");
        Assert.state(!ClientContainer.existed(reqVo.getClientId()), "Dup clientId!");

        registerService.register(reqVo);
        return RemoteAPI.register2adminRsp;
    }

    @RequestMapping("/online/nodes")
    public String getOnlineList() {
        return JSON.toJSONString(ClientContainer.onlineClient());
    }

}
