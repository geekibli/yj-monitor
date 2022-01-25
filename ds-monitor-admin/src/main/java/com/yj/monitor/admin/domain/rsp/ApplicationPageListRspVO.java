package com.yj.monitor.admin.domain.rsp;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午5:55
 * @Version 1.0
 */
public class ApplicationPageListRspVO {
    private List<ApplicationPageListRsp> applicationList;
    private Long total;
    private Integer page;

    public ApplicationPageListRspVO() {
    }

    public ApplicationPageListRspVO(List<ApplicationPageListRsp> applicationList, Long total, Integer page) {
        this.applicationList = applicationList;
        this.total = total;
        this.page = page;
    }

    public List<ApplicationPageListRsp> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<ApplicationPageListRsp> applicationList) {
        this.applicationList = applicationList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
