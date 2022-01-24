package com.yj.monitor.admin.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.disruptor.MonitorEventFactory;
import com.yj.monitor.admin.disruptor.MonitorEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:37
 * @Version 1.0
 */
@Configuration
public class DisruptorConfiguration {

    @Bean("ringBuffer")
    public RingBuffer<MonitorEvent> monitorRingBuffer() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Disruptor<MonitorEvent> disruptor = new Disruptor<MonitorEvent>(new MonitorEventFactory(), 1024 * 1024, executor,
                ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MonitorEventHandler());
        disruptor.start();
        RingBuffer<MonitorEvent> ringBuffer = disruptor.getRingBuffer();
        return ringBuffer;
    }

}
