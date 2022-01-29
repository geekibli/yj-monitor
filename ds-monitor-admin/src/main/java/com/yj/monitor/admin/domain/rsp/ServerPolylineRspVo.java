package com.yj.monitor.admin.domain.rsp;


import java.util.Date;
import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午4:17
 * @Version 1.0
 */
public class ServerPolylineRspVo {
    private List<Date> createTime;
    private List<Long> totalDisk;
    private List<Long> freeDisk;
    private List<Long> usedDisk;
    private List<Double> systemLoadAverage;
    private List<Double> cpuTotal;
    private List<Double> cpuSys;
    private List<Double> cpuUser;
    private List<Double> cpuFree;
    private List<Double> cpuWait;

    public ServerPolylineRspVo() {
    }

    public ServerPolylineRspVo(List<Date> createTime, List<Long> totalDisk, List<Long> freeDisk, List<Long> usedDisk, List<Double> systemLoadAverage, List<Double> cpuTotal, List<Double> cpuSys, List<Double> cpuUser, List<Double> cpuFree, List<Double> cpuWait) {
        this.createTime = createTime;
        this.totalDisk = totalDisk;
        this.freeDisk = freeDisk;
        this.usedDisk = usedDisk;
        this.systemLoadAverage = systemLoadAverage;
        this.cpuTotal = cpuTotal;
        this.cpuSys = cpuSys;
        this.cpuUser = cpuUser;
        this.cpuFree = cpuFree;
        this.cpuWait = cpuWait;
    }

    public List<Date> getCreateTime() {
        return createTime;
    }

    public void setCreateTime(List<Date> createTime) {
        this.createTime = createTime;
    }

    public List<Long> getTotalDisk() {
        return totalDisk;
    }

    public void setTotalDisk(List<Long> totalDisk) {
        this.totalDisk = totalDisk;
    }

    public List<Long> getFreeDisk() {
        return freeDisk;
    }

    public void setFreeDisk(List<Long> freeDisk) {
        this.freeDisk = freeDisk;
    }

    public List<Double> getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(List<Double> systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public List<Double> getCpuTotal() {
        return cpuTotal;
    }

    public void setCpuTotal(List<Double> cpuTotal) {
        this.cpuTotal = cpuTotal;
    }

    public List<Double> getCpuSys() {
        return cpuSys;
    }

    public void setCpuSys(List<Double> cpuSys) {
        this.cpuSys = cpuSys;
    }

    public List<Double> getCpuUser() {
        return cpuUser;
    }

    public void setCpuUser(List<Double> cpuUser) {
        this.cpuUser = cpuUser;
    }

    public List<Double> getCpuFree() {
        return cpuFree;
    }

    public void setCpuFree(List<Double> cpuFree) {
        this.cpuFree = cpuFree;
    }

    public List<Double> getCpuWait() {
        return cpuWait;
    }

    public void setCpuWait(List<Double> cpuWait) {
        this.cpuWait = cpuWait;
    }

    public List<Long> getUsedDisk() {
        return usedDisk;
    }

    public void setUsedDisk(List<Long> usedDisk) {
        this.usedDisk = usedDisk;
    }
}
