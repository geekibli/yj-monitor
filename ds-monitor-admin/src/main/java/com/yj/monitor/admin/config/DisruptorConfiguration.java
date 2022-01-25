package com.yj.monitor.admin.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.disruptor.MonitorEventFactory;
import com.yj.monitor.admin.disruptor.MonitorEventHandler;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.admin.service.PullMonitorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:37
 * @Version 1.0
 */
@Component
@DependsOn(value = {"pullMonitorService"})
public class DisruptorConfiguration {

    @Resource
    private PullMonitorService pullMonitorService;

    @Bean("ringBuffer")
    public RingBuffer<MonitorEvent> monitorRingBuffer() {
        Disruptor<MonitorEvent> disruptor = new Disruptor<MonitorEvent>(new MonitorEventFactory(), 1024 * 1024, MonitorExecutor.build(),
                ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MonitorEventHandler(pullMonitorService));
        disruptor.start();
        RingBuffer<MonitorEvent> ringBuffer = disruptor.getRingBuffer();
        return ringBuffer;
    }

}
