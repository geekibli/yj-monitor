package com.yj.monitor.admin.runner;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorMemoryPartition;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午7:21
 * @Version 1.0
 */
public class PullMemoryPartitionTask implements Callable<List<MonitorMemoryPartition>> {

    private final Logger logger = LoggerFactory.getLogger(PullMemoryPartitionTask.class);

    private MonitorEvent monitorEvent;

    public PullMemoryPartitionTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public List<MonitorMemoryPartition> call() throws Exception {

        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }

        HttpResponse response = HttpUtil.createPost(monitorEvent.getClient().getMonitorUrl())
                .header("Content-Type", "application/json")
                .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.MEMORY_PARTITION.getcName(), MonitorMethods.MEMORY_PARTITION.getmName(), new Object[0])))
                .execute();
        logger.info("classload {}", response.body());


        return null;

    }
}
