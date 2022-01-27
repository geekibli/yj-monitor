package com.yj.monitor.admin.runner;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorMemoryPartition;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.domain.MemoryPartition;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rpc.MonitorApi;
import com.yj.monitor.rpc.client.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午7:21
 * @Version 1.0
 */
public class PullMemoryPartitionTask implements Callable<List<MonitorMemoryPartition>> {

    private final Logger logger = LoggerFactory.getLogger(PullMemoryPartitionTask.class);

    private final MonitorEvent monitorEvent;

    public PullMemoryPartitionTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public List<MonitorMemoryPartition> call() throws Exception {
        return v2();
    }


    private List<MonitorMemoryPartition> v2() {
        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }

        MonitorApi api = new RpcClient(monitorEvent.getNode().getRpcAddress()).create(MonitorApi.class);
        List<MemoryPartition> partitions = api.getMemoryPartitions();

        return partitions.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }


    private List<MonitorMemoryPartition> v1() {
        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }

        HttpResponse response = HttpUtil.createPost(monitorEvent.getNode().getMonitorUrl())
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.MEMORY_PARTITION.getcName(), MonitorMethods.MEMORY_PARTITION.getmName(), new Object[0])))
                .execute();

        JSONObject bodyJson = JSON.parseObject(response.body());
        String data = bodyJson.getString("data");
        List<MemoryPartition> partitions = new ArrayList<>(JSON.parseArray(data, MemoryPartition.class));

        return partitions.stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }

    private MonitorMemoryPartition convert(MemoryPartition partition) {
        MonitorMemoryPartition memoryPartition = new MonitorMemoryPartition();
        memoryPartition.setBatchId(monitorEvent.getBatchId());
        memoryPartition.setClientAddress(monitorEvent.getNode().getAddress());
        memoryPartition.setClientId(monitorEvent.getNode().getClientId());
        memoryPartition.setPartitionName(partition.getName());
        BeanUtils.copyProperties(partition, memoryPartition);
        return memoryPartition;
    }
}
