package com.yj.monitor.admin.disruptor;

import com.lmax.disruptor.EventHandler;
import com.yj.monitor.admin.service.PullMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:19
 * @Version 1.0
 */
@Component
public class MonitorEventHandler implements EventHandler<MonitorEvent> {

    private final Logger logger = LoggerFactory.getLogger(MonitorEventHandler.class);

    private final PullMonitorService pullMonitorService;

    public MonitorEventHandler(PullMonitorService pullMonitorService) {
        this.pullMonitorService = pullMonitorService;
    }

    @Override
    public void onEvent(MonitorEvent monitorEvent, long l, boolean b) {
        try {
            pullMonitorService.handleDisruptor(monitorEvent, l, b);
        } catch (Exception e) {
            logger.error("onEvent ....", e);
            // TODO record and alarm
        }
    }
}
