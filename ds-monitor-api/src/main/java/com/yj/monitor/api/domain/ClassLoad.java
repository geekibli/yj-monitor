package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/27 上午10:35
 * @Version 1.0
 */
public class ClassLoad implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long loaderClassCount;
    private Long totalLoaderClassCount;
    private Long unloadedClassCount;
    private Boolean verbose;

    public ClassLoad(){}

    public Long getLoaderClassCount() {
        return loaderClassCount;
    }

    public void setLoaderClassCount(Long loaderClassCount) {
        this.loaderClassCount = loaderClassCount;
    }

    public Long getTotalLoaderClassCount() {
        return totalLoaderClassCount;
    }

    public void setTotalLoaderClassCount(Long totalLoaderClassCount) {
        this.totalLoaderClassCount = totalLoaderClassCount;
    }

    public Long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setUnloadedClassCount(Long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }

    public Boolean getVerbose() {
        return verbose;
    }

    public void setVerbose(Boolean verbose) {
        this.verbose = verbose;
    }
}
