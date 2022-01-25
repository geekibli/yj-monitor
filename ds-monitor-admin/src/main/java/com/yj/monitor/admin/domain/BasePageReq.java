package com.yj.monitor.admin.domain;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午5:50
 * @Version 1.0
 */
public class BasePageReq {

    private Integer pageNum;
    private Integer pageSize;

    private static Integer DEFAULT_PAGE_NUM = 1;
    private static Integer DEFAULT_PAGE_SIZE = 10;

    public void pageDefault() {
        pageNum = DEFAULT_PAGE_NUM;
        pageSize = DEFAULT_PAGE_SIZE;
    }

    public void pageDefaultIfNull() {
        if (pageNum == null) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
    }

    public void pageDefault(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public void pageDefaultIfNull(Integer pageNum, Integer pageSize) {
        if (this.pageNum == null) {
            this.pageNum = pageNum;
        }
        if (this.pageSize == null) {
            this.pageSize = pageSize;
        }
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
