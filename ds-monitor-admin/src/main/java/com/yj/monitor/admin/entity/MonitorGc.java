package com.yj.monitor.admin.entity;

import java.util.Date;

/**
 * monitor_gc
 * @author gaolei
 */
public class MonitorGc {
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
     * 客户端id
     */
    private String clientId;

    /**
     * 收集时间
     */
    private Long psMarksweepCollectionTime;

    /**
     * 收集次数
     */
    private Long psMarksweepCollectionCount;

    /**
     * 收集时间
     */
    private Long psScavengeCollectionTime;

    /**
     * 收集次数
     */
    private Long psScavengeCollectionCount;

    /**
     * 实时数据大小
     */
    private Long liveDataSize;

    /**
     * 数据大小最大值
     */
    private Long maxDataSize;

    /**
     * 内存分配次数
     */
    private Long memoryAllocatedCount;

    /**
     * 内存计数
     */
    private Long memoryPromotedCount;

    /**
     * 暂停次数
     */
    private Long pauseCount;

    /**
     * 暂停总时间
     */
    private Double pauseTotalTime;

    /**
     * 
     */
    private Long pauseMax;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0-未删除 1-删除
     */
    private Integer isDeleted;

    public MonitorGc() {
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

    public Long getPsMarksweepCollectionTime() {
        return psMarksweepCollectionTime;
    }

    public void setPsMarksweepCollectionTime(Long psMarksweepCollectionTime) {
        this.psMarksweepCollectionTime = psMarksweepCollectionTime;
    }

    public Long getPsMarksweepCollectionCount() {
        return psMarksweepCollectionCount;
    }

    public void setPsMarksweepCollectionCount(Long psMarksweepCollectionCount) {
        this.psMarksweepCollectionCount = psMarksweepCollectionCount;
    }

    public Long getPsScavengeCollectionTime() {
        return psScavengeCollectionTime;
    }

    public void setPsScavengeCollectionTime(Long psScavengeCollectionTime) {
        this.psScavengeCollectionTime = psScavengeCollectionTime;
    }

    public Long getPsScavengeCollectionCount() {
        return psScavengeCollectionCount;
    }

    public void setPsScavengeCollectionCount(Long psScavengeCollectionCount) {
        this.psScavengeCollectionCount = psScavengeCollectionCount;
    }

    public Long getLiveDataSize() {
        return liveDataSize;
    }

    public void setLiveDataSize(Long liveDataSize) {
        this.liveDataSize = liveDataSize;
    }

    public Long getMaxDataSize() {
        return maxDataSize;
    }

    public void setMaxDataSize(Long maxDataSize) {
        this.maxDataSize = maxDataSize;
    }

    public Long getMemoryAllocatedCount() {
        return memoryAllocatedCount;
    }

    public void setMemoryAllocatedCount(Long memoryAllocatedCount) {
        this.memoryAllocatedCount = memoryAllocatedCount;
    }

    public Long getMemoryPromotedCount() {
        return memoryPromotedCount;
    }

    public void setMemoryPromotedCount(Long memoryPromotedCount) {
        this.memoryPromotedCount = memoryPromotedCount;
    }

    public Long getPauseCount() {
        return pauseCount;
    }

    public void setPauseCount(Long pauseCount) {
        this.pauseCount = pauseCount;
    }

    public Double getPauseTotalTime() {
        return pauseTotalTime;
    }

    public void setPauseTotalTime(Double pauseTotalTime) {
        this.pauseTotalTime = pauseTotalTime;
    }

    public Long getPauseMax() {
        return pauseMax;
    }

    public void setPauseMax(Long pauseMax) {
        this.pauseMax = pauseMax;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}