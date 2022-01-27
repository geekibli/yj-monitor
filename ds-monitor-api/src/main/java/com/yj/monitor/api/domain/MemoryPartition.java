package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/24 下午2:22
 * @Version 1.0
 */
public class MemoryPartition implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String partitionType;
    private String manages;
    private Long usageInit;
    private Long usageUsed;
    private Long usageCommitted;
    private Long usageMax;
    private Long peakUsageMax;
    private Long peakUsageInit;
    private Long peakUsageUsed;
    private Long peakUsageCommitted;
    private Long collectionUsageMax;
    private Long collectionUsageInit;
    private Long collectionUsageUsed;
    private Long collectionUsageCommitted;


    public MemoryPartition() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartitionType() {
        return partitionType;
    }

    public void setPartitionType(String partitionType) {
        this.partitionType = partitionType;
    }

    public Long getUsageInit() {
        return usageInit;
    }

    public void setUsageInit(Long usageInit) {
        this.usageInit = usageInit;
    }

    public Long getUsageUsed() {
        return usageUsed;
    }

    public void setUsageUsed(Long usageUsed) {
        this.usageUsed = usageUsed;
    }

    public Long getUsageCommitted() {
        return usageCommitted;
    }

    public void setUsageCommitted(Long usageCommitted) {
        this.usageCommitted = usageCommitted;
    }

    public Long getUsageMax() {
        return usageMax;
    }

    public void setUsageMax(Long usageMax) {
        this.usageMax = usageMax;
    }

    public Long getPeakUsageMax() {
        return peakUsageMax;
    }

    public void setPeakUsageMax(Long peakUsageMax) {
        this.peakUsageMax = peakUsageMax;
    }

    public Long getPeakUsageInit() {
        return peakUsageInit;
    }

    public void setPeakUsageInit(Long peakUsageInit) {
        this.peakUsageInit = peakUsageInit;
    }

    public Long getPeakUsageUsed() {
        return peakUsageUsed;
    }

    public void setPeakUsageUsed(Long peakUsageUsed) {
        this.peakUsageUsed = peakUsageUsed;
    }

    public Long getPeakUsageCommitted() {
        return peakUsageCommitted;
    }

    public void setPeakUsageCommitted(Long peakUsageCommitted) {
        this.peakUsageCommitted = peakUsageCommitted;
    }

    public String getManages() {
        return manages;
    }

    public void setManages(String manages) {
        this.manages = manages;
    }

    public Long getCollectionUsageMax() {
        return collectionUsageMax;
    }

    public void setCollectionUsageMax(Long collectionUsageMax) {
        this.collectionUsageMax = collectionUsageMax;
    }

    public Long getCollectionUsageInit() {
        return collectionUsageInit;
    }

    public void setCollectionUsageInit(Long collectionUsageInit) {
        this.collectionUsageInit = collectionUsageInit;
    }

    public Long getCollectionUsageUsed() {
        return collectionUsageUsed;
    }

    public void setCollectionUsageUsed(Long collectionUsageUsed) {
        this.collectionUsageUsed = collectionUsageUsed;
    }

    public Long getCollectionUsageCommitted() {
        return collectionUsageCommitted;
    }

    public void setCollectionUsageCommitted(Long collectionUsageCommitted) {
        this.collectionUsageCommitted = collectionUsageCommitted;
    }
}
