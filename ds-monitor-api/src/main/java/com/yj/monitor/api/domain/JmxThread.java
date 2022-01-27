package com.yj.monitor.api.domain;


import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/27 上午10:13
 * @Version 1.0
 */
public class JmxThread implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long totalStartedThreadCount;
    private Integer threadCount;
    private String deadLockedThreads;
    private Long currentThreadCpuTime;
    private Long currentThreadUserTime;
    private Integer daemonThreadCount;
    private Integer peakThreadCount;
    private Boolean isCurrentThreadCpuTimeSupported;
    private Boolean isObjectMonitorUsageSupported;
    private Boolean isSynchronizerUsageSupported;
    private Boolean isThreadContentionMonitoringEnabled;
    private Boolean isThreadContentionMonitoringSupported;
    private Boolean isThreadCpuTimeEnabled;
    private Boolean isThreadCpuTimeSupported;

    public JmxThread() {
    }

    public Long getTotalStartedThreadCount() {
        return totalStartedThreadCount;
    }

    public void setTotalStartedThreadCount(Long totalStartedThreadCount) {
        this.totalStartedThreadCount = totalStartedThreadCount;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public String getDeadLockedThreads() {
        return deadLockedThreads;
    }

    public void setDeadLockedThreads(String deadLockedThreads) {
        this.deadLockedThreads = deadLockedThreads;
    }

    public Long getCurrentThreadCpuTime() {
        return currentThreadCpuTime;
    }

    public void setCurrentThreadCpuTime(Long currentThreadCpuTime) {
        this.currentThreadCpuTime = currentThreadCpuTime;
    }

    public Long getCurrentThreadUserTime() {
        return currentThreadUserTime;
    }

    public void setCurrentThreadUserTime(Long currentThreadUserTime) {
        this.currentThreadUserTime = currentThreadUserTime;
    }

    public Integer getDaemonThreadCount() {
        return daemonThreadCount;
    }

    public void setDaemonThreadCount(Integer daemonThreadCount) {
        this.daemonThreadCount = daemonThreadCount;
    }

    public Integer getPeakThreadCount() {
        return peakThreadCount;
    }

    public void setPeakThreadCount(Integer peakThreadCount) {
        this.peakThreadCount = peakThreadCount;
    }

    public Boolean getCurrentThreadCpuTimeSupported() {
        return isCurrentThreadCpuTimeSupported;
    }

    public void setCurrentThreadCpuTimeSupported(Boolean currentThreadCpuTimeSupported) {
        isCurrentThreadCpuTimeSupported = currentThreadCpuTimeSupported;
    }

    public Boolean getObjectMonitorUsageSupported() {
        return isObjectMonitorUsageSupported;
    }

    public void setObjectMonitorUsageSupported(Boolean objectMonitorUsageSupported) {
        isObjectMonitorUsageSupported = objectMonitorUsageSupported;
    }

    public Boolean getSynchronizerUsageSupported() {
        return isSynchronizerUsageSupported;
    }

    public void setSynchronizerUsageSupported(Boolean synchronizerUsageSupported) {
        isSynchronizerUsageSupported = synchronizerUsageSupported;
    }

    public Boolean getThreadContentionMonitoringEnabled() {
        return isThreadContentionMonitoringEnabled;
    }

    public void setThreadContentionMonitoringEnabled(Boolean threadContentionMonitoringEnabled) {
        isThreadContentionMonitoringEnabled = threadContentionMonitoringEnabled;
    }

    public Boolean getThreadContentionMonitoringSupported() {
        return isThreadContentionMonitoringSupported;
    }

    public void setThreadContentionMonitoringSupported(Boolean threadContentionMonitoringSupported) {
        isThreadContentionMonitoringSupported = threadContentionMonitoringSupported;
    }

    public Boolean getThreadCpuTimeEnabled() {
        return isThreadCpuTimeEnabled;
    }

    public void setThreadCpuTimeEnabled(Boolean threadCpuTimeEnabled) {
        isThreadCpuTimeEnabled = threadCpuTimeEnabled;
    }

    public Boolean getThreadCpuTimeSupported() {
        return isThreadCpuTimeSupported;
    }

    public void setThreadCpuTimeSupported(Boolean threadCpuTimeSupported) {
        isThreadCpuTimeSupported = threadCpuTimeSupported;
    }
}
