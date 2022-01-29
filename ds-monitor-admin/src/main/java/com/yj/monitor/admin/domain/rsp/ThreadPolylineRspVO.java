package com.yj.monitor.admin.domain.rsp;

import java.util.Date;
import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午3:24
 * @Version 1.0
 */
public class ThreadPolylineRspVO {

    private List<Date> createTime;

    private List<Long> totalStartThreadCount;

    private List<Long> threadCount;

    private List<Long> peakCount;

    private List<Long> daemonCount;

    public ThreadPolylineRspVO() {
    }

    public ThreadPolylineRspVO(List<Date> createTime, List<Long> totalStartThreadCount, List<Long> threadCount, List<Long> peakCount, List<Long> daemonCount) {
        this.createTime = createTime;
        this.totalStartThreadCount = totalStartThreadCount;
        this.threadCount = threadCount;
        this.peakCount = peakCount;
        this.daemonCount = daemonCount;
    }

    public List<Date> getCreateTime() {
        return createTime;
    }

    public void setCreateTime(List<Date> createTime) {
        this.createTime = createTime;
    }

    public List<Long> getTotalStartThreadCount() {
        return totalStartThreadCount;
    }

    public void setTotalStartThreadCount(List<Long> totalStartThreadCount) {
        this.totalStartThreadCount = totalStartThreadCount;
    }

    public List<Long> getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(List<Long> threadCount) {
        this.threadCount = threadCount;
    }

    public List<Long> getPeakCount() {
        return peakCount;
    }

    public void setPeakCount(List<Long> peakCount) {
        this.peakCount = peakCount;
    }

    public List<Long> getDaemonCount() {
        return daemonCount;
    }

    public void setDaemonCount(List<Long> daemonCount) {
        this.daemonCount = daemonCount;
    }
}
