package com.yj.monitor.admin.controller;

import com.yj.monitor.admin.service.DiscardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author gaolei
 * @Date 2022/1/21 下午5:19
 * @Version 1.0
 */
@RestController
public class DiscardController {

    private final Logger logger = LoggerFactory.getLogger(DiscardController.class);

    @Resource
    private DiscardService discardService;

    @GetMapping("/discard")
    public String discardClient(@RequestParam("clientId") @Valid @NotBlank String clientId) {
        return discardService.discardClient(clientId);
    }


}
