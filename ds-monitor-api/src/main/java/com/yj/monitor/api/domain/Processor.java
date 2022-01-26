package com.yj.monitor.api.domain;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/26 上午10:42
 * @Version 1.0
 */
public class Processor {

    private Long contextSwitches;
    private Boolean cpu64bit;
    private String family;
    private String identifier;
    private Long interrupts;
    private Integer logicalProcessorCount;
    private String model;
    private String name;
    private Integer physicalPackageCount;
    private Integer physicalProcessorCount;
    private String processorId;
    private String stepping;
    private Double systemCpuLoad;
    private Double systemCpuLoadBetweenTicks;
    private Double systemLoadAverage;
    private Long systemUptime;
    private String vendor;
    private Long vendorFreq;
    private List<Integer> systemCpuLoadTicks;


    public Processor() {
    }

    public Long getContextSwitches() {
        return contextSwitches;
    }

    public void setContextSwitches(Long contextSwitches) {
        this.contextSwitches = contextSwitches;
    }

    public Boolean getCpu64bit() {
        return cpu64bit;
    }

    public void setCpu64bit(Boolean cpu64bit) {
        this.cpu64bit = cpu64bit;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Long getInterrupts() {
        return interrupts;
    }

    public void setInterrupts(Long interrupts) {
        this.interrupts = interrupts;
    }

    public Integer getLogicalProcessorCount() {
        return logicalProcessorCount;
    }

    public void setLogicalProcessorCount(Integer logicalProcessorCount) {
        this.logicalProcessorCount = logicalProcessorCount;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhysicalPackageCount() {
        return physicalPackageCount;
    }

    public void setPhysicalPackageCount(Integer physicalPackageCount) {
        this.physicalPackageCount = physicalPackageCount;
    }

    public Integer getPhysicalProcessorCount() {
        return physicalProcessorCount;
    }

    public void setPhysicalProcessorCount(Integer physicalProcessorCount) {
        this.physicalProcessorCount = physicalProcessorCount;
    }

    public String getProcessorId() {
        return processorId;
    }

    public void setProcessorId(String processorId) {
        this.processorId = processorId;
    }

    public String getStepping() {
        return stepping;
    }

    public void setStepping(String stepping) {
        this.stepping = stepping;
    }

    public Double getSystemCpuLoad() {
        return systemCpuLoad;
    }

    public void setSystemCpuLoad(Double systemCpuLoad) {
        this.systemCpuLoad = systemCpuLoad;
    }

    public Double getSystemCpuLoadBetweenTicks() {
        return systemCpuLoadBetweenTicks;
    }

    public void setSystemCpuLoadBetweenTicks(Double systemCpuLoadBetweenTicks) {
        this.systemCpuLoadBetweenTicks = systemCpuLoadBetweenTicks;
    }

    public Double getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(Double systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public Long getSystemUptime() {
        return systemUptime;
    }

    public void setSystemUptime(Long systemUptime) {
        this.systemUptime = systemUptime;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Long getVendorFreq() {
        return vendorFreq;
    }

    public void setVendorFreq(Long vendorFreq) {
        this.vendorFreq = vendorFreq;
    }

    public List<Integer> getSystemCpuLoadTicks() {
        return systemCpuLoadTicks;
    }

    public void setSystemCpuLoadTicks(List<Integer> systemCpuLoadTicks) {
        this.systemCpuLoadTicks = systemCpuLoadTicks;
    }
}
