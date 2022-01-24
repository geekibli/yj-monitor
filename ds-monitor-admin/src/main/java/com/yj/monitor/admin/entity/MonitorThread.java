package com.yj.monitor.admin.entity;

import java.util.Date;

/**
 * monitor_thread
 * @author gaolei
 */
public class MonitorThread {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 批次id
     */
    private Long batchId;

    /**
     * 客户端地址
     */
    private String clientAddress;

    /**
     * 客户端名称
     */
    private String clientId;

    /**
     * 开始线程总数
     */
    private Long totalStartedCount;

    /**
     * 死锁的线程
     */
    private String deadlockThreads;

    /**
     * 线程高峰
     */
    private Long peakCount;

    /**
     * 当前运行线程数
     */
    private Long threadCount;

    /**
     * 守护进程数
     */
    private Long daemonCount;

    /**
     * 创建时间
     */
    private Date createTimestamp;

    /**
     * 更新时间
     */
    private Date updateTimestamp;

    /**
     * 0-未删除 1-删除
     */
    private Integer isDeleted;


    public MonitorThread() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getTotalStartedCount() {
        return totalStartedCount;
    }

    public void setTotalStartedCount(Long totalStartedCount) {
        this.totalStartedCount = totalStartedCount;
    }

    public String getDeadlockThreads() {
        return deadlockThreads;
    }

    public void setDeadlockThreads(String deadlockThreads) {
        this.deadlockThreads = deadlockThreads;
    }

    public Long getPeakCount() {
        return peakCount;
    }

    public void setPeakCount(Long peakCount) {
        this.peakCount = peakCount;
    }

    public Long getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Long threadCount) {
        this.threadCount = threadCount;
    }

    public Long getDaemonCount() {
        return daemonCount;
    }

    public void setDaemonCount(Long daemonCount) {
        this.daemonCount = daemonCount;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}