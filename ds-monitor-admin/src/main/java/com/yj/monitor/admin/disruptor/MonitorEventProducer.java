package com.yj.monitor.admin.disruptor;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.RingBuffer;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.Client;
import com.yj.monitor.api.domain.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:16
 * @Version 1.0
 */
@Component
public class MonitorEventProducer {

    private final Logger logger = LoggerFactory.getLogger(MonitorEventProducer.class);

    @Resource
    private RingBuffer<MonitorEvent> ringBuffer;

    public synchronized void onData(Client client) {
        Long batchId = IdUtil.getSnowflake().nextId();
        for (Method method : MonitorMethods.SCHEDULE_MONITOR_METHODS) {
            long sequence = ringBuffer.next();
            MonitorEvent monitorEvent = ringBuffer.get(sequence);
            monitorEvent.setBatchId(batchId);
            monitorEvent.setClient(client);
            monitorEvent.setMethod(method);
            ringBuffer.publish(sequence);
            logger.info("Disruptor publish monitor event, sequence: {} , event: {}", sequence, JSON.toJSONString(monitorEvent));
        }
    }
}
