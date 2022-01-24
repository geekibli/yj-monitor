package com.yj.monitor.admin.disruptor;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;
import com.yj.monitor.admin.entity.MonitorThread;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.admin.runner.PullClassLoadTask;
import com.yj.monitor.admin.runner.PullMemoryTask;
import com.yj.monitor.admin.runner.PullThreadTask;
import com.yj.monitor.api.domain.Client;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午5:19
 * @Version 1.0
 */
public class MonitorEventHandler implements EventHandler<MonitorEvent> {

    @Override
    public void onEvent(MonitorEvent monitorEvent, long l, boolean b) throws Exception {
        System.err.println("消费数据   " + JSON.toJSONString(monitorEvent));

        // {"batchId":1485562321706885123,"client":{"address":"/127.0.0.1:58499","clientId":"yjmonitorcore001","host":"192.168.50.154","monitorUrl":"http://192.168.50.154:10000/monitor","port":10000}}


        Future<MonitorThread> threadFuture = MonitorExecutor.build().submit(new PullThreadTask(monitorEvent));


        MonitorEvent event= new MonitorEvent();
        event.setBatchId(1485562321706885123L);
        Client client = new Client();
        client.setAddress("/127.0.0.1:58499");
        client.setClientId("yjmonitorcore001");
        client.setMonitorUrl("http://192.168.50.154:10000/monitor");
        client.setHost("192.168.50.154");
        client.setPort(10000);
        event.setClient(client);





    }
}
