package com.yj.monitor.admin.runner;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.Client;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午7:56
 * @Version 1.0
 */
public class PullGcTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(PullGcTask.class);

    private final List<Client> clients;

    public PullGcTask(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void run() {
        if (CollectionUtils.isEmpty(clients)) {
            return;
        }

        for (Client client : clients) {
            HttpResponse response = HttpUtil.createPost(client.getMonitorUrl())
                    .header("Content-Type", "application/json")
                    .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.GC.getcName(), MonitorMethods.GC.getmName(), new Object[0])))
                    .execute();
            logger.info("gc {}", response.body());
        }

    }
}
