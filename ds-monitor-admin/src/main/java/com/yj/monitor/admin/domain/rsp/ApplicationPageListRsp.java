package com.yj.monitor.admin.domain.rsp;

import java.util.Date;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午5:52
 * @Version 1.0
 */
public class ApplicationPageListRsp {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端地址
     */
    private String clientAddress;

    /**
     * 服务名称
     */
    private String applicationName;

    /**
     * 服务器系统 Linux / mac
     */
    private String systemType;

    /**
     * 启动次数
     */
    private Integer loadTimes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    private Boolean isOnline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public Integer getLoadTimes() {
        return loadTimes;
    }

    public void setLoadTimes(Integer loadTimes) {
        this.loadTimes = loadTimes;
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

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }
}
