package com.yj.monitor.admin.entity;

import java.util.Date;


/**
 * monitor_classload
 * @author gaolei
 */

public class MonitorClassLoad {
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
     * 已经加载类数量
     */
    private Long loadedClassCount;

    /**
     * 未加载类数量
     */
    private Long unloadedClassCount;

    /**
     * 总数量
     */
    private Long totalLoadedClassCount;

    /**
     * 是否开启打印类加载信息
     */
    private String verbose;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 0-未删除 1-删除
     */
    private Integer idDeleted;


    public MonitorClassLoad() {
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

    public Long getLoadedClassCount() {
        return loadedClassCount;
    }

    public void setLoadedClassCount(Long loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    public Long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setUnloadedClassCount(Long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }

    public Long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public void setTotalLoadedClassCount(Long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
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