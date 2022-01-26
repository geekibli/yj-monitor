package com.yj.monitor.api.domain;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午8:39
 * @Version 1.0
 */
public class Disk {

    private Long total;
    private Long free;
    private Long threshold;

    public Disk() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getFree() {
        return free;
    }

    public void setFree(Long free) {
        this.free = free;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }
}
