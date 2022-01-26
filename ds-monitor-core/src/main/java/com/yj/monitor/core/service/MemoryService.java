package com.yj.monitor.core.service;

import cn.hutool.system.oshi.OshiUtil;
import com.yj.monitor.api.domain.Mem;
import com.yj.monitor.core.handler.JDKManagementHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import oshi.hardware.GlobalMemory;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/26 下午12:16
 * @Version 1.0
 */
@Service
public class MemoryService {

    private final Logger logger = LoggerFactory.getLogger(MemoryService.class);

    @Resource
    private JDKManagementHandler jdkManagementHandler;

    public Mem getMemory() {
        Mem memory = jdkManagementHandler.getMemoryInfo();
        GlobalMemory gm = OshiUtil.getMemory();
        memory.setTotal(gm.getTotal());
        memory.setSwapUsed(gm.getSwapUsed());
        memory.setSwapTotal(gm.getSwapTotal());
        memory.setSwapPagesOut(gm.getSwapPagesOut());
        memory.setSwapPagesIn(gm.getSwapPagesIn());
        memory.setPageSize(gm.getPageSize());
        memory.setAvailable(gm.getAvailable());
        return memory;
    }

}
