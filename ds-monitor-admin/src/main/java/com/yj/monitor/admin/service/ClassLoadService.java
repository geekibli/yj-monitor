package com.yj.monitor.admin.service;

import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorClassLoad;
import com.yj.monitor.admin.mapper.MonitorClassLoadMapper;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.admin.runner.PullClassLoadTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author gaolei
 * @Date 2022/1/25 上午11:41
 * @Version 1.0
 */
@Service
public class ClassLoadService {

    private final Logger logger = LoggerFactory.getLogger(ClassLoadService.class);

    @Resource
    private MonitorClassLoadMapper monitorClassLoadMapper;

    public void saveClassLoad(MonitorEvent event) {
        Future<MonitorClassLoad> future = MonitorExecutor.build().submit(new PullClassLoadTask(event));
        MonitorClassLoad classLoad = null;
        try {
            classLoad = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (null == classLoad) {
            return;
        }
        monitorClassLoadMapper.insertSelective(classLoad);
    }
}
