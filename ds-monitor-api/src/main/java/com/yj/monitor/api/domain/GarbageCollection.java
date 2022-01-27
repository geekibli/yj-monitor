package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/27 上午11:23
 * @Version 1.0
 */
public class GarbageCollection implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public GarbageCollection() {
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
}
