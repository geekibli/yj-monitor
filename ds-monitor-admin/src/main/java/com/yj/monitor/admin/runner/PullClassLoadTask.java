package com.yj.monitor.admin.runner;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.entity.MonitorClassLoad;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rsp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午7:02
 * @Version 1.0
 */
public class PullClassLoadTask implements Callable<MonitorClassLoad> {

    private final Logger logger = LoggerFactory.getLogger(PullClassLoadTask.class);

    private final MonitorEvent monitorEvent;

    public PullClassLoadTask(MonitorEvent monitorEvent) {
        this.monitorEvent = monitorEvent;
    }

    @Override
    public MonitorClassLoad call() throws Exception {

        if (null == monitorEvent || monitorEvent.notValid()) {
            return null;
        }

        HttpResponse response = HttpUtil.createPost(monitorEvent.getNode().getMonitorUrl())
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .body(JSON.toJSONString(new RemoteMonitorReqVO(MonitorMethods.CLASSLOADER.getcName(), MonitorMethods.CLASSLOADER.getmName(), new Object[0])))
                .execute();

        Response invokeRspVO = JSON.parseObject(response.body(), Response.class);
        Map<String,String> map = (Map<String,String>) invokeRspVO.getData();
        MonitorClassLoad classLoad = new MonitorClassLoad();
        classLoad.setBatchId(monitorEvent.getBatchId());
        classLoad.setClientAddress(monitorEvent.getNode().getAddress());
        classLoad.setClientId(monitorEvent.getNode().getClientId());
        classLoad.setLoadedClassCount(Long.valueOf(map.get("LoadedClassCount")));
        classLoad.setTotalLoadedClassCount(Long.valueOf(map.get("TotalLoadedClassCount")));
        classLoad.setUnloadedClassCount(Long.valueOf(map.get("UnloadedClassCount")));
        classLoad.setVerbose(map.get("Verbose"));
        return classLoad;
    }

}
