package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/26 上午11:20
 * @Version 1.0
 */
public class OperatingSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer bitness;
    private String manufacturer;
    private String family;
    private Integer processCount;
    private Integer processId;
    private Integer threadCount;
    private String version;
    private Process process;

    public OperatingSystem() {
    }

    public Integer getBitness() {
        return bitness;
    }

    public void setBitness(Integer bitness) {
        this.bitness = bitness;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getProcessCount() {
        return processCount;
    }

    public void setProcessCount(Integer processCount) {
        this.processCount = processCount;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}
