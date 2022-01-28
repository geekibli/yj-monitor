package com.yj.monitor.admin.service;

import com.yj.monitor.admin.domain.RegisterCenter;
import com.yj.monitor.admin.domain.rsp.ServerRspVO;
import com.yj.monitor.admin.runner.MonitorExecutor;
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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
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


}
