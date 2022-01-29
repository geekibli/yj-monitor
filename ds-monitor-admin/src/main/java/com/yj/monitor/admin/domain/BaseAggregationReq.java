package com.yj.monitor.admin.domain;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午3:18
 * @Version 1.0
 */
public class BaseAggregationReq {

    private Date fromDate;

    private Date toDate;

    public BaseAggregationReq(){}

    public BaseAggregationReq(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void defaultIfNull(){
        toDate = new Date();
        fromDate = DateUtil.offsetDay(toDate, -14);
    }

}
