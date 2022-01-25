package com.yj.monitor.core.service;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.constant.RemoteAPI;
import com.yj.monitor.api.req.ClientRegisterReqVO;
import com.yj.monitor.core.config.MonitorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午3:34
 * @Version 1.0
 */
@Service
public class ClientRegisterService {

    private final Logger logger = LoggerFactory.getLogger(ClientRegisterService.class);

    @Resource
    private MonitorConfig monitorConfig;
    @Resource
    private ClientHeartService clientHeartService;

    /**
     * 1、把当前结点信息注册到admin
     * 2、注册成功之后开启心跳
     * 3、注册失败之后，根据重试策略多次尝试注册
     * 4、多次注册都没有成功，根据配置抛出异常
     *
     * @param freq 重试次数递归变量
     */
    public void register2Admin(int freq) {

        if (freq > monitorConfig.getRetryTime()) {
            return;
        }

        if (!monitorConfig.getMonitorOpen()) {
            logger.warn("Please make sure the monitor open config is closed!");
            return;
        }

        try {
            HttpResponse execute = HttpUtil.createPost(monitorConfig.getAdminUrl() + RemoteAPI.REGISTER_2_ADMIN)
                    .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                    .body(JSON.toJSONString(genRegisterReq()))
                    .execute();

            if (RemoteAPI.REGISTER_2_ADMIN_RSP.equals(execute.body())) {
                logger.info("Monitor register to admin success!");
                clientHeartService.sendHeart(monitorConfig.getAdminConfig().getHost(), monitorConfig.getHeartPort());
                return;
            }

            logger.warn("Monitor register to admin fail! " + execute.body());
            if (monitorConfig.getRetry()) {
                while (freq < monitorConfig.getRetryTime()) {
                    freq++;
                    register2Admin(freq);
                }
            }
            if (monitorConfig.getThrowAble()) {
                throw new RuntimeException("monitor register admin error!");
            }
        } catch (Exception e) {
            logger.warn("Monitor register to admin fail! ", e);

            if (monitorConfig.getRetry()) {
                while (freq < monitorConfig.getRetryTime()) {
                    freq++;
                    register2Admin(freq);
                }
            }

            if (monitorConfig.getThrowAble()) {
                throw new RuntimeException("monitor register admin error!", e);
            }
        }
    }

    private ClientRegisterReqVO genRegisterReq() {
        ClientRegisterReqVO reqVO = new ClientRegisterReqVO();
        reqVO.setApplicationName(monitorConfig.getApplicationName());
        reqVO.setClientHost(monitorConfig.getLocalHost());
        reqVO.setClientPort(monitorConfig.getApplicationPort());
        reqVO.setClientId(monitorConfig.getClientId());
        reqVO.setAuthToken(monitorConfig.getAuthToken());
        return reqVO;
    }

}
