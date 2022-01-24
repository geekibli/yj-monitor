package com.yj.monitor.admin.entity;

import java.util.Date;

/**
 * monitor_memory_partition
 * @author gaolei
 */
public class MonitorMemoryPartition {
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
     * 分区名称
     */
    private String partitionName;

    /**
     * 堆类型
     */
    private String partitionType;

    /**
     * 内存管理器
     */
    private String manages;

    /**
     * 还可以使用
     */
    private Long usageCommitted;

    /**
     * 初始值
     */
    private Long usageInit;

    /**
     * 最大值
     */
    private Long usageMax;

    /**
     * 已经使用
     */
    private Long usageUsed;

    /**
     * 还可以使用
     */
    private Long peakUsageCommitted;

    /**
     * 初始值
     */
    private Long peakUsageInit;

    /**
     * 最大值
     */
    private Long peakUsageMax;

    /**
     * 已经使用
     */
    private Long peakUsageUsed;

    /**
     * 还可以使用
     */
    private Long collectionUsageCommitted;

    /**
     * 最大值
     */
    private Long collectionUsageMax;

    /**
     * 初始值
     */
    private Long collectionUsageInit;

    /**
     * 已经使用
     */
    private Long collectionUsageUsed;

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

    public MonitorMemoryPartition() {
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

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public String getPartitionType() {
        return partitionType;
    }

    public void setPartitionType(String partitionType) {
        this.partitionType = partitionType;
    }

    public String getManages() {
        return manages;
    }

    public void setManages(String manages) {
        this.manages = manages;
    }

    public Long getUsageCommitted() {
        return usageCommitted;
    }

    public void setUsageCommitted(Long usageCommitted) {
        this.usageCommitted = usageCommitted;
    }

    public Long getUsageInit() {
        return usageInit;
    }

    public void setUsageInit(Long usageInit) {
        this.usageInit = usageInit;
    }

    public Long getUsageMax() {
        return usageMax;
    }

    public void setUsageMax(Long usageMax) {
        this.usageMax = usageMax;
    }

    public Long getUsageUsed() {
        return usageUsed;
    }

    public void setUsageUsed(Long usageUsed) {
        this.usageUsed = usageUsed;
    }

    public Long getPeakUsageCommitted() {
        return peakUsageCommitted;
    }

    public void setPeakUsageCommitted(Long peakUsageCommitted) {
        this.peakUsageCommitted = peakUsageCommitted;
    }

    public Long getPeakUsageInit() {
        return peakUsageInit;
    }

    public void setPeakUsageInit(Long peakUsageInit) {
        this.peakUsageInit = peakUsageInit;
    }

    public Long getPeakUsageMax() {
        return peakUsageMax;
    }

    public void setPeakUsageMax(Long peakUsageMax) {
        this.peakUsageMax = peakUsageMax;
    }

    public Long getPeakUsageUsed() {
        return peakUsageUsed;
    }

    public void setPeakUsageUsed(Long peakUsageUsed) {
        this.peakUsageUsed = peakUsageUsed;
    }

    public Long getCollectionUsageCommitted() {
        return collectionUsageCommitted;
    }

    public void setCollectionUsageCommitted(Long collectionUsageCommitted) {
        this.collectionUsageCommitted = collectionUsageCommitted;
    }

    public Long getCollectionUsageMax() {
        return collectionUsageMax;
    }

    public void setCollectionUsageMax(Long collectionUsageMax) {
        this.collectionUsageMax = collectionUsageMax;
    }

    public Long getCollectionUsageInit() {
        return collectionUsageInit;
    }

    public void setCollectionUsageInit(Long collectionUsageInit) {
        this.collectionUsageInit = collectionUsageInit;
    }

    public Long getCollectionUsageUsed() {
        return collectionUsageUsed;
    }

    public void setCollectionUsageUsed(Long collectionUsageUsed) {
        this.collectionUsageUsed = collectionUsageUsed;
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