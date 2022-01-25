package com.yj.monitor.admin.service;

import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorGc;
import com.yj.monitor.admin.mapper.MonitorGcMapper;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.admin.runner.PullGcTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author gaolei
 * @Date 2022/1/25 上午11:44
 * @Version 1.0
 */
@Service
public class GcService {

    private final Logger logger = LoggerFactory.getLogger(GcService.class);

    @Resource
    private MonitorGcMapper monitorGcMapper;

    public void saveGc(MonitorEvent event) {
        Future<MonitorGc> future = MonitorExecutor.build().submit(new PullGcTask(event));
        MonitorGc gc = null;
        try {
            gc = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (null == gc) {
            return;
        }

        monitorGcMapper.insertSelective(gc);
    }

}
