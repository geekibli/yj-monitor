package com.yj.monitor.admin.entity;

import java.util.Date;

/**
 * monitor_memory
 * @author gaolei
 */
public class MonitorMemory {
    /**
     * 
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
     * java虚拟机能使用的内存量
     */
    private Long heapCommitted;

    /**
     * 当前已经使用的内存量
     */
    private Long heapUsed;

    /**
     * 初始内存容量
     */
    private Long heapInit;

    /**
     * 内存管理的最大内存量
     */
    private Long heapMax;

    /**
     * 
     */
    private Long nonHeapCommitted;

    /**
     * 
     */
    private Long nonHeapInit;

    /**
     * 
     */
    private Long nonHeapMax;

    /**
     * 
     */
    private Long nonHeapUsed;

    /**
     * 物理总内存
     */
    private Long total;

    /**
     * 可用物理内存
     */
    private Long available;

    /**
     * 
     */
    private Long pageSize;

    /**
     * 
     */
    private Long swapPagesIn;

    /**
     * 
     */
    private Long swapPagesOut;

    /**
     * 总交换区大小
     */
    private Long swapTotal;

    /**
     * 交换区可用大小
     */
    private Long swapUsed;

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

    public MonitorMemory() {
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

    public Long getHeapCommitted() {
        return heapCommitted;
    }

    public void setHeapCommitted(Long heapCommitted) {
        this.heapCommitted = heapCommitted;
    }

    public Long getHeapUsed() {
        return heapUsed;
    }

    public void setHeapUsed(Long heapUsed) {
        this.heapUsed = heapUsed;
    }

    public Long getHeapInit() {
        return heapInit;
    }

    public void setHeapInit(Long heapInit) {
        this.heapInit = heapInit;
    }

    public Long getHeapMax() {
        return heapMax;
    }

    public void setHeapMax(Long heapMax) {
        this.heapMax = heapMax;
    }

    public Long getNonHeapCommitted() {
        return nonHeapCommitted;
    }

    public void setNonHeapCommitted(Long nonHeapCommitted) {
        this.nonHeapCommitted = nonHeapCommitted;
    }

    public Long getNonHeapInit() {
        return nonHeapInit;
    }

    public void setNonHeapInit(Long nonHeapInit) {
        this.nonHeapInit = nonHeapInit;
    }

    public Long getNonHeapMax() {
        return nonHeapMax;
    }

    public void setNonHeapMax(Long nonHeapMax) {
        this.nonHeapMax = nonHeapMax;
    }

    public Long getNonHeapUsed() {
        return nonHeapUsed;
    }

    public void setNonHeapUsed(Long nonHeapUsed) {
        this.nonHeapUsed = nonHeapUsed;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getSwapPagesIn() {
        return swapPagesIn;
    }

    public void setSwapPagesIn(Long swapPagesIn) {
        this.swapPagesIn = swapPagesIn;
    }

    public Long getSwapPagesOut() {
        return swapPagesOut;
    }

    public void setSwapPagesOut(Long swapPagesOut) {
        this.swapPagesOut = swapPagesOut;
    }

    public Long getSwapTotal() {
        return swapTotal;
    }

    public void setSwapTotal(Long swapTotal) {
        this.swapTotal = swapTotal;
    }

    public Long getSwapUsed() {
        return swapUsed;
    }

    public void setSwapUsed(Long swapUsed) {
        this.swapUsed = swapUsed;
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