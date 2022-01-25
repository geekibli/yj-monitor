package com.yj.monitor.admin.runner;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEventProducer;
import com.yj.monitor.admin.domain.RegisterCenter;
import com.yj.monitor.api.domain.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:34
 * @Version 1.0
 */
public class PullMonitorTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(PullMonitorTask.class);

    private final MonitorEventProducer monitorEventProducer;

    public PullMonitorTask(MonitorEventProducer monitorEventProducer) {
        this.monitorEventProducer = monitorEventProducer;
    }

    @Override
    public void run() {
        List<Node> nodes = RegisterCenter.onlineClient();
        if (CollectionUtils.isEmpty(nodes)) {
            return;
        }
        logger.info("Current online node: {}", JSON.toJSONString(nodes));
        for (Node node : nodes) {
            monitorEventProducer.onData(node);
        }
    }
}
