package com.yj.monitor.admin.service;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.disruptor.MonitorEventProducer;
import com.yj.monitor.admin.runner.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private MonitorEventProducer monitorEventProducer;
    @Resource
    private MemoryService memoryService;
    @Resource
    private ThreadService threadService;
    @Resource
    private ClassLoadService classLoadService;
    @Resource
    private GcService gcService;

    /**
     * 定时执行
     * 1、读取客户端注册列表
     * 2、生成客户端任务，通过disruptor进行发布
     * 3、disruptor 按照顺序消费
     */
    public synchronized void execute() {
        logger.info("【 Monitor admin 】start schedule job. ");
        MonitorScheduleExecutor.build().scheduleWithFixedDelay(new PullMonitorTask(monitorEventProducer), 0, 30, TimeUnit.SECONDS);
    }


    /**
     * disruptor 消费者处理逻辑
     *
     * @param monitorEvent 监控时间
     * @param l            任务序列号
     * @param b            b
     */
    public void handleDisruptor(MonitorEvent monitorEvent, long l, boolean b) {
        switch (monitorEvent.getMethod().getmName()) {
            case "getMemoryInfo":
                memoryService.saveMemory(monitorEvent);
                break;
            case "getThreadInfo":
                threadService.saveThread(monitorEvent);
                break;
            case "getClassLoader":
                classLoadService.saveClassLoad(monitorEvent);
                break;
            case "getMemoryPartition":
                memoryService.savePartitions(monitorEvent);
                break;
            case "getGcInfo":
                gcService.saveGc(monitorEvent);
                break;
            default:
                logger.info("无响应的MonitorEvent : sequence-{}  event:{}", l, JSON.toJSONString(monitorEvent));
        }
        logger.info("监控事件消费完成 : sequence-{} event:{}", l, monitorEvent.getBatchId());
    }


}
