package com.yj.monitor.admin.service;

import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorMemory;
import com.yj.monitor.admin.entity.MonitorMemoryPartition;
import com.yj.monitor.admin.mapper.MonitorMemoryMapper;
import com.yj.monitor.admin.mapper.MonitorMemoryPartitionMapper;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.admin.runner.PullMemoryPartitionTask;
import com.yj.monitor.admin.runner.PullMemoryTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author gaolei
 * @Date 2022/1/25 上午11:31
 * @Version 1.0
 */
@Service
public class MemoryService {

    private final Logger logger = LoggerFactory.getLogger(MemoryService.class);

    @Resource
    private MonitorMemoryMapper monitorMemoryMapper;
    @Resource
    private MonitorMemoryPartitionMapper monitorMemoryPartitionMapper;

    public void saveMemory(MonitorEvent event) {
        Future<MonitorMemory> memoryFuture = MonitorExecutor.build().submit(new PullMemoryTask(event));
        MonitorMemory memory = null;
        try {
            memory = memoryFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (null == memory) {
            return;
        }
        monitorMemoryMapper.insertSelective(memory);
    }


    public void savePartitions(MonitorEvent event) {
        Future<List<MonitorMemoryPartition>> memoryPartitionFuture = MonitorExecutor.build().submit(new PullMemoryPartitionTask(event));
        List<MonitorMemoryPartition> partitions = null;
        try {
            partitions = memoryPartitionFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (null == partitions || partitions.isEmpty()) {
            return;
        }

        // TODO
        for (MonitorMemoryPartition partition : partitions) {
            monitorMemoryPartitionMapper.insertSelective(partition);
        }
    }

}
