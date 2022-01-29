package com.yj.monitor.admin.service;

import cn.hutool.core.date.DateUtil;
import com.yj.monitor.admin.disruptor.MonitorEvent;
import com.yj.monitor.admin.domain.req.ThreadpolylineReqVO;
import com.yj.monitor.admin.domain.rsp.ThreadPolylineRspVO;
import com.yj.monitor.admin.entity.MonitorThread;
import com.yj.monitor.admin.mapper.MonitorThreadMapper;
import com.yj.monitor.admin.mapper.ext.MonitorThreadExtMapper;
import com.yj.monitor.admin.runner.MonitorExecutor;
import com.yj.monitor.admin.runner.PullThreadTask;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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
    @Resource
    private MonitorThreadExtMapper monitorThreadExtMapper;

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


    public ThreadPolylineRspVO polyline(ThreadpolylineReqVO reqVO) {
        List<MonitorThread> list = monitorThreadExtMapper.getList(reqVO.getFromDate(), reqVO.getToDate());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<Date> createTime = Lists.newArrayList();
        List<Long> totalStartThreadCount = Lists.newArrayList();
        List<Long> threadCount = Lists.newArrayList();
        List<Long> peakCount = Lists.newArrayList();
        List<Long> daemonCount = Lists.newArrayList();


        for (MonitorThread thread : list) {
            createTime.add(thread.getCreateTimestamp());
            totalStartThreadCount.add(thread.getTotalStartedCount());
            threadCount.add(thread.getThreadCount());
            peakCount.add(thread.getPeakCount());
            daemonCount.add(thread.getDaemonCount());
        }
        return new ThreadPolylineRspVO(createTime, totalStartThreadCount, threadCount, peakCount, daemonCount);
    }
}
