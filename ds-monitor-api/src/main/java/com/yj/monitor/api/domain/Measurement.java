package com.yj.monitor.api.domain;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午8:54
 * @Version 1.0
 */

public class Measurement {
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
