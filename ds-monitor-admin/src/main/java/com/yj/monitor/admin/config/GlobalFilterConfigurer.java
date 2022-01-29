package com.yj.monitor.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author lbm
 * @date 2020/9/9 5:10 下午
 **/
@Configuration
@ConfigurationProperties(prefix = "app.config.global-filter")
public class GlobalFilterConfigurer {
    private List<String> ignorePaths;

    public GlobalFilterConfigurer() {
    }

    public List<String> getIgnorePaths() {
        return ignorePaths;
    }

    public void setIgnorePaths(List<String> ignorePaths) {
        this.ignorePaths = ignorePaths;
    }
}
