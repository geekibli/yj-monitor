package com.yj.monitor.admin.runner;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorGc;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午7:56
 * @Version 1.0
 */
public class PullGcTask implements Callable<MonitorGc> {

    private final Logger logger = LoggerFactory.getLogger(PullGcTask.class);

    private  MonitorEvent monitorEvent;

    public PullGcTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public MonitorGc call() throws Exception {
        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }

        HttpResponse response = HttpUtil.createPost(monitorEvent.getClient().getMonitorUrl())
                .header("Content-Type", "application/json")
                .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.GC.getcName(), MonitorMethods.GC.getmName(), new Object[0])))
                .execute();
        logger.info("gc {}", response.body());


        return null;
    }

}
