package com.yj.monitor.api.domain;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午7:47
 * @Version 1.0
 */
public class Server {

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


    private Long diskTotal;
    private Long diskFree;
    private Long diskThreshold;

    private String systemCpuUsage;


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

    public String getSystemCpuUsage() {
        return systemCpuUsage;
    }

    public void setSystemCpuUsage(String systemCpuUsage) {
        this.systemCpuUsage = systemCpuUsage;
    }
}
