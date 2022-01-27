package com.yj.monitor.admin.service;

import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorThread;
import com.yj.monitor.admin.mapper.MonitorThreadMapper;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.admin.runner.PullThreadTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author gaolei
 * @Date 2022/1/25 上午11:37
 * @Version 1.0
 */
@Service
public class ThreadService {

    private final Logger logger = LoggerFactory.getLogger(ThreadService.class);

    @Resource
    private MonitorThreadMapper monitorThreadMapper;

    public void saveThread(MonitorEvent event) {
        Future<MonitorThread> threadFuture = MonitorExecutor.build().submit(new PullThreadTask(event));
        MonitorThread thread = null;
        try {
            thread = threadFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (null == thread) {
            return;
        }
        monitorThreadMapper.insertSelective(thread);
    }
}
