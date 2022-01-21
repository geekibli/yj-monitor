package com.yj.monitor.admin.config;

/**
 * @Author gaolei
 * @Date 2022/1/21 下午2:44
 * @Version 1.0
 */
public class HeartConfig {

    private Integer port;
    private Integer readerIdleTime;
    private Integer writerIdleTime;
    private Integer allIdleTime;
    private Integer failTime;

    public Integer getFailTime() {
        return failTime;
    }

    public void setFailTime(Integer failTime) {
        this.failTime = failTime;
    }

    public Integer getReaderIdleTime() {
        return readerIdleTime;
    }

    public void setReaderIdleTime(Integer readerIdleTime) {
        this.readerIdleTime = readerIdleTime;
    }

    public Integer getWriterIdleTime() {
        return writerIdleTime;
    }

    public void setWriterIdleTime(Integer writerIdleTime) {
        this.writerIdleTime = writerIdleTime;
    }

    public Integer getAllIdleTime() {
        return allIdleTime;
    }

    public void setAllIdleTime(Integer allIdleTime) {
        this.allIdleTime = allIdleTime;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
