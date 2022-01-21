package com.yj.monitor.admin.runner;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.doman.ClientContainer;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.Client;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;


import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午5:06
 * @Version 1.0
 */
public class PullMemoryTask implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(PullMemoryTask.class);


    public PullMemoryTask() {
    }


    @Override
    public void run() {

        if (CollectionUtils.isEmpty(ClientContainer.onlineClient())) {
            return;
        }else {
            logger.info("node count :{}" , ClientContainer.onlineClient().size());
        }

        for (Client client : ClientContainer.onlineClient()) {
            RemoteMonitorReqVO reqVO = new RemoteMonitorReqVO();
            reqVO.setClassName(MonitorMethods.MEMORY.getcName());
            reqVO.setMethod(MonitorMethods.MEMORY.getmName());
            reqVO.setParams(new Object[0]);
            HttpResponse response = HttpUtil.createPost(client.getMonitorUrl())
                    .header("Content-Type", "application/json")
                    .body(JSON.toJSONString(reqVO))
                    .execute();

            logger.info(client.getPort() + " memory: {}", response.body());
        }
    }
}
