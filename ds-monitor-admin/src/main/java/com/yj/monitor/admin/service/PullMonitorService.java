package com.yj.monitor.admin.service;

import com.yj.monitor.admin.config.AdminMonitorConfig;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.disruptor.MonitorEventProducer;
import com.yj.monitor.admin.doman.ClientContainer;
import com.yj.monitor.admin.entity.*;
import com.yj.monitor.admin.mapper.*;
import com.yj.monitor.admin.runner.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午5:30
 * @Version 1.0
 */
@Service
public class PullMonitorService {

    private final Logger logger = LoggerFactory.getLogger(PullMonitorService.class);

    @Resource
    private AdminMonitorConfig adminMonitorConfig;
    @Resource
    private MonitorEventProducer monitorEventProducer;
    @Resource
    private MonitorMemoryMapper monitorMemoryMapper;
    @Resource
    private MonitorMemoryPartitionMapper monitorMemoryPartitionMapper;
    @Resource
    private MonitorThreadMapper monitorThreadMapper;
    @Resource
    private MonitorClassLoadMapper monitorClassLoadMapper;
    @Resource
    private MonitorGcMapper monitorGcMapper;

    /**
     * 定时执行
     * 1、读取客户端注册列表
     * 2、生成客户端任务，通过disruptor进行发布
     * 3、disruptor 按照顺序消费
     */
    public synchronized void execute() {
        logger.info("【 Monitor admin 】start schedule job. ");
        MonitorPool.build().scheduleWithFixedDelay(new PullMonitorTask(monitorEventProducer), 0, 5, TimeUnit.SECONDS);
    }


    /**
     * disruptor 消费者处理逻辑
     *
     * @param monitorEvent 监控时间
     * @param l            任务序列号
     * @param b            b
     */
    public void handleDisruptor(MonitorEvent monitorEvent, long l, boolean b) {
        Future<MonitorThread> threadFuture = MonitorExecutor.build().submit(new PullThreadTask(monitorEvent));
        Future<MonitorMemory> memoryFuture = MonitorExecutor.build().submit(new PullMemoryTask(monitorEvent));
        Future<List<MonitorMemoryPartition>> memoryPartitionFuture = MonitorExecutor.build().submit(new PullMemoryPartitionTask(monitorEvent));
        Future<MonitorClassLoad> memoryClassLoadFuture = MonitorExecutor.build().submit(new PullClassLoadTask(monitorEvent));
        Future<MonitorGc> memoryGcFuture = MonitorExecutor.build().submit(new PullGcTask(monitorEvent));








    }


}
