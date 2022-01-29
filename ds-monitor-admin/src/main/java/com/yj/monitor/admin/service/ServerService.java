package com.yj.monitor.admin.service;

import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.domain.RegisterCenter;
import com.yj.monitor.admin.domain.req.ServerPolylineReqVO;
import com.yj.monitor.admin.domain.rsp.ServerPolylineRspVo;
import com.yj.monitor.admin.domain.rsp.ServerRspVO;
import com.yj.monitor.admin.entity.MonitorServer;
import com.yj.monitor.admin.mapper.MonitorServerMapper;
import com.yj.monitor.admin.mapper.ext.MonitorServerExtMapper;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.admin.runner.PullServerTask;
import com.yj.monitor.api.domain.Node;
import com.yj.monitor.api.domain.Server;
import com.yj.monitor.api.rpc.MonitorApi;
import com.yj.monitor.api.util.FormatUtils;
import com.yj.monitor.rpc.client.RpcClient;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午7:45
 * @Version 1.0 服务器相关逻辑 admin
 */
@Service
public class ServerService {

    private final Logger logger = LoggerFactory.getLogger(ServerService.class);

    @Resource
    private MonitorServerMapper monitorServerMapper;
    @Resource
    private MonitorServerExtMapper monitorServerExtMapper;

    public List<ServerRspVO> getServer() {
        List<Node> nodes = RegisterCenter.onlineClient();
        if (CollectionUtils.isEmpty(nodes)) {
            return Lists.emptyList();
        }

        Set<String> nodeHosts = nodes.stream().map(Node::getClientHost).collect(Collectors.toSet());
        Map<String, Node> nodeMap = nodes.stream().collect(Collectors.toMap(Node::getClientHost, Function.identity(), (o1, o2) -> o1));

        List<ServerRspVO> servers = Lists.newArrayList();
        CountDownLatch cdl = new CountDownLatch(nodeHosts.size());

        for (String host : nodeHosts) {
            Node node = nodeMap.get(host);
            MonitorExecutor.build()
                    .execute(() -> {
                        MonitorApi monitorApi = new RpcClient(node.getRpcAddress()).create(MonitorApi.class);
                        Server serverInfo = monitorApi.getServerInfo();
                        ServerRspVO rspVO = new ServerRspVO();
                        BeanUtils.copyProperties(serverInfo, rspVO);
                        rspVO.setDiskThreshold(FormatUtils.formatSize(serverInfo.getDiskThreshold()));
                        rspVO.setDiskFree(FormatUtils.formatSize(serverInfo.getDiskFree()));
                        rspVO.setDiskTotal(FormatUtils.formatSize(serverInfo.getDiskTotal()));
                        rspVO.setServerHost(node.getClientHost());
                        servers.add(rspVO);
                        cdl.countDown();
                    });
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return servers;
    }


    public void saveThread(MonitorEvent event) {
        Future<MonitorServer> future = MonitorExecutor.build().submit(new PullServerTask(event));
        MonitorServer server = null;
        try {
            server = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (server == null) {
            return;
        }
        monitorServerMapper.insertSelective(server);
    }


    public ServerPolylineRspVo polyline(ServerPolylineReqVO reqVO) {
        List<MonitorServer> list = monitorServerExtMapper.getList(reqVO.getFromDate(), reqVO.getToDate(), reqVO.getClientId());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<Date> createTime = Lists.newArrayList();
        List<Long> totalDisk = Lists.newArrayList();
        List<Long> freeDisk = Lists.newArrayList();
        List<Long> usedDisk = Lists.newArrayList();
        List<Double> systemLoadAverage = Lists.newArrayList();
        List<Double> cpuTotal = Lists.newArrayList();
        List<Double> cpuSys = Lists.newArrayList();
        List<Double> cpuUser = Lists.newArrayList();
        List<Double> cpuFree = Lists.newArrayList();
        List<Double> cpuWait = Lists.newArrayList();

        for (MonitorServer server : list) {
            createTime.add(server.getCreateTime());
            totalDisk.add(server.getDiskTotal());
            freeDisk.add(server.getDiskFree());
            usedDisk.add(server.getDiskTotal() - server.getDiskFree());
            systemLoadAverage.add(server.getSystemLoadAverage());
            cpuTotal.add(server.getCpuTotalUsage());
            cpuSys.add(server.getCpuSysUsage());
            cpuUser.add(server.getCpuUserUsage());
            cpuFree.add(server.getCpuFree());
            cpuWait.add(server.getCpuWait());
        }

        return new ServerPolylineRspVo(createTime, totalDisk, freeDisk, usedDisk, systemLoadAverage, cpuTotal, cpuSys, cpuUser, cpuFree, cpuWait);

    }


}
