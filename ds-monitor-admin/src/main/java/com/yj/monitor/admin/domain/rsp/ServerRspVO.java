package com.yj.monitor.admin.domain.rsp;

/**
 * @Author gaolei
 * @Date 2022/1/28 上午10:30
 * @Version 1.0
 */
public class ServerRspVO {

    /**
     * mac/linux
     */
    private String serverType;
    private Integer availableProcessors;
    /**
     * x86_64
     */
    private String arch;
    private String systemLoadAverage;

    private String userName;
    private String userCountry;
    private String sunManagementCompile;
    private String jvmName;
    private String jvmInfo;


    private String diskTotal;
    private String diskFree;
    private String diskThreshold;

    private String systemCpuUsage;

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

    public ServerRspVO() {
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public Integer getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(Integer availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(String systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getSunManagementCompile() {
        return sunManagementCompile;
    }

    public void setSunManagementCompile(String sunManagementCompile) {
        this.sunManagementCompile = sunManagementCompile;
    }

    public String getJvmName() {
        return jvmName;
    }

    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }

    public String getJvmInfo() {
        return jvmInfo;
    }

    public void setJvmInfo(String jvmInfo) {
        this.jvmInfo = jvmInfo;
    }

    public String getDiskTotal() {
        return diskTotal;
    }

    public void setDiskTotal(String diskTotal) {
        this.diskTotal = diskTotal;
    }

    public String getDiskFree() {
        return diskFree;
    }

    public void setDiskFree(String diskFree) {
        this.diskFree = diskFree;
    }

    public String getDiskThreshold() {
        return diskThreshold;
    }

    public void setDiskThreshold(String diskThreshold) {
        this.diskThreshold = diskThreshold;
    }

    public String getSystemCpuUsage() {
        return systemCpuUsage;
    }

    public void setSystemCpuUsage(String systemCpuUsage) {
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
}
