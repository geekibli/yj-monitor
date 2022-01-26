package com.yj.monitor.api.domain;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/26 上午9:51
 * @Version 1.0
 */
public class NetWork {
    private Long bytesRecv;
    private Integer bytesSent;
    private String displayName;
    private Integer inErrors;
    private Integer mTU;
    private String macAddr;
    private String name;
    private Integer outErrors;
    private Integer packetsRecv;
    private Integer packetsSent;
    private Integer speed;
    private Long timeStamp;
    private List<String> iPv4addr;
    private List<String> iPv6addr;

    public NetWork() {
    }

    public Long getBytesRecv() {
        return bytesRecv;
    }

    public void setBytesRecv(Long bytesRecv) {
        this.bytesRecv = bytesRecv;
    }

    public Integer getBytesSent() {
        return bytesSent;
    }

    public void setBytesSent(Integer bytesSent) {
        this.bytesSent = bytesSent;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getInErrors() {
        return inErrors;
    }

    public void setInErrors(Integer inErrors) {
        this.inErrors = inErrors;
    }

    public Integer getmTU() {
        return mTU;
    }

    public void setmTU(Integer mTU) {
        this.mTU = mTU;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOutErrors() {
        return outErrors;
    }

    public void setOutErrors(Integer outErrors) {
        this.outErrors = outErrors;
    }

    public Integer getPacketsRecv() {
        return packetsRecv;
    }

    public void setPacketsRecv(Integer packetsRecv) {
        this.packetsRecv = packetsRecv;
    }

    public Integer getPacketsSent() {
        return packetsSent;
    }

    public void setPacketsSent(Integer packetsSent) {
        this.packetsSent = packetsSent;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<String> getiPv4addr() {
        return iPv4addr;
    }

    public void setiPv4addr(List<String> iPv4addr) {
        this.iPv4addr = iPv4addr;
    }

    public List<String> getiPv6addr() {
        return iPv6addr;
    }

    public void setiPv6addr(List<String> iPv6addr) {
        this.iPv6addr = iPv6addr;
    }
}
