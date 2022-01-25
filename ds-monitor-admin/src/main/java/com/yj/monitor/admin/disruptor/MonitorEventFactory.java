package com.yj.monitor.admin.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:12
 * @Version 1.0
 */
public class MonitorEventFactory implements EventFactory<MonitorEvent> {

    @Override
    public MonitorEvent newInstance() {
        MonitorEvent event = new MonitorEvent();
//        event.setBatchId(IdUtil.getSnowflake().nextId());
        return event;
    }

}
