package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午8:54
 * @Version 1.0
 */

public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;

    private String statistic;
    private String value;

    public Measurement() {
    }

    public String getStatistic() {
        return statistic;
    }

    public void setStatistic(String statistic) {
        this.statistic = statistic;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
