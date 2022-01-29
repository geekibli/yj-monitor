package com.yj.monitor.admin.entity;

import java.util.Date;

/**
 * monitor_server
 * @author gaolei
 */
public class MonitorServer {
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
     * 系统负载
     */
    private Double systemLoadAverage;

    /**
     * 磁盘总量
     */
    private Long diskTotal;

    /**
     * 可用磁盘
     */
    private Long diskFree;

    /**
     * 磁盘阈值
     */
    private Long diskThreshold;

    /**
     * cpu使用
     */
    private Double systemCpuUsage;

    /**
     * 服务器ip
     */
    private String serverHost;

    /**
     * cpu使用率
     */
    private Double cpuTotalUsage;

    /**
     * cpu系统使用率
     */
    private Double cpuSysUsage;

    /**
     * cpu用户使用率
     */
    private Double cpuUserUsage;

    /**
     * cpu等待率
     */
    private Double cpuWait;

    /**
     * cpu可用
     */
    private Double cpuFree;

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
    private Integer idDeleted;

    public MonitorServer() {
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

    public Double getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(Double systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public Long getDiskTotal() {
        return diskTotal;
    }

    public void setDiskTotal(Long diskTotal) {
        this.diskTotal = diskTotal;
    }

    public Long getDiskFree() {
        return diskFree;
    }

    public void setDiskFree(Long diskFree) {
        this.diskFree = diskFree;
    }

    public Long getDiskThreshold() {
        return diskThreshold;
    }

    public void setDiskThreshold(Long diskThreshold) {
        this.diskThreshold = diskThreshold;
    }

    public Double getSystemCpuUsage() {
        return systemCpuUsage;
    }

    public void setSystemCpuUsage(Double systemCpuUsage) {
        this.systemCpuUsage = systemCpuUsage;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public Double getCpuTotalUsage() {
        return cpuTotalUsage;
    }

    public void setCpuTotalUsage(Double cpuTotalUsage) {
        this.cpuTotalUsage = cpuTotalUsage;
    }

    public Double getCpuSysUsage() {
        return cpuSysUsage;
    }

    public void setCpuSysUsage(Double cpuSysUsage) {
        this.cpuSysUsage = cpuSysUsage;
    }

    public Double getCpuUserUsage() {
        return cpuUserUsage;
    }

    public void setCpuUserUsage(Double cpuUserUsage) {
        this.cpuUserUsage = cpuUserUsage;
    }

    public Double getCpuWait() {
        return cpuWait;
    }

    public void setCpuWait(Double cpuWait) {
        this.cpuWait = cpuWait;
    }

    public Double getCpuFree() {
        return cpuFree;
    }

    public void setCpuFree(Double cpuFree) {
        this.cpuFree = cpuFree;
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

    public Integer getIdDeleted() {
        return idDeleted;
    }

    public void setIdDeleted(Integer idDeleted) {
        this.idDeleted = idDeleted;
    }
}