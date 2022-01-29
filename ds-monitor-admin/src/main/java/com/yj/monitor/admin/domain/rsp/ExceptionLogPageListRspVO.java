package com.yj.monitor.admin.domain.rsp;

import com.yj.monitor.admin.entity.MonitorExceptionLog;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午2:31
 * @Version 1.0
 */
public class ExceptionLogPageListRspVO {

    private List<MonitorExceptionLog> logList;

    private Integer page;

    private Long total;

    public ExceptionLogPageListRspVO(){}

    public ExceptionLogPageListRspVO(List<MonitorExceptionLog> logList, Integer page, Long total) {
        this.logList = logList;
        this.page = page;
        this.total = total;
    }

    public List<MonitorExceptionLog> getLogList() {
        return logList;
    }

    public void setLogList(List<MonitorExceptionLog> logList) {
        this.logList = logList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
