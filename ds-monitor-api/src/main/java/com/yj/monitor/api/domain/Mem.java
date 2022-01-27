package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/26 上午9:09
 * @Version 1.0c
 */
public class Mem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long available;
    private Long pageSize;
    private Long swapPagesIn;
    private Long swapPagesOut;
    private Long swapTotal;
    private Long swapUsed;
    private Long total;
    private Long heapCommitted;
    private Long heapInit;
    private Long heapUsed;
    private Long heapMax;
    private Long nonHeapCommitted;
    private Long nonHeapInit;
    private Long nonHeapUsed;
    private Long nonHeapMax;

    public Mem() {
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getSwapPagesIn() {
        return swapPagesIn;
    }

    public void setSwapPagesIn(Long swapPagesIn) {
        this.swapPagesIn = swapPagesIn;
    }

    public Long getSwapPagesOut() {
        return swapPagesOut;
    }

    public void setSwapPagesOut(Long swapPagesOut) {
        this.swapPagesOut = swapPagesOut;
    }

    public Long getSwapTotal() {
        return swapTotal;
    }

    public void setSwapTotal(Long swapTotal) {
        this.swapTotal = swapTotal;
    }

    public Long getSwapUsed() {
        return swapUsed;
    }

    public void setSwapUsed(Long swapUsed) {
        this.swapUsed = swapUsed;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getHeapCommitted() {
        return heapCommitted;
    }

    public void setHeapCommitted(Long heapCommitted) {
        this.heapCommitted = heapCommitted;
    }

    public Long getHeapInit() {
        return heapInit;
    }

    public void setHeapInit(Long heapInit) {
        this.heapInit = heapInit;
    }

    public Long getHeapUsed() {
        return heapUsed;
    }

    public void setHeapUsed(Long heapUsed) {
        this.heapUsed = heapUsed;
    }

    public Long getHeapMax() {
        return heapMax;
    }

    public void setHeapMax(Long heapMax) {
        this.heapMax = heapMax;
    }

    public Long getNonHeapCommitted() {
        return nonHeapCommitted;
    }

    public void setNonHeapCommitted(Long nonHeapCommitted) {
        this.nonHeapCommitted = nonHeapCommitted;
    }

    public Long getNonHeapInit() {
        return nonHeapInit;
    }

    public void setNonHeapInit(Long nonHeapInit) {
        this.nonHeapInit = nonHeapInit;
    }

    public Long getNonHeapUsed() {
        return nonHeapUsed;
    }

    public void setNonHeapUsed(Long nonHeapUsed) {
        this.nonHeapUsed = nonHeapUsed;
    }

    public Long getNonHeapMax() {
        return nonHeapMax;
    }

    public void setNonHeapMax(Long nonHeapMax) {
        this.nonHeapMax = nonHeapMax;
    }
}
