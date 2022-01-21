package com.yj.monitor.api.constant;

import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午4:21
 * @Version 1.0
 */
public interface Intervals {


     long MILLISECOND = TimeUnit.MILLISECONDS.toMillis(1);

     long SECOND = TimeUnit.SECONDS.toMillis(1);

     long MINUTES = TimeUnit.MINUTES.toMillis(1);

     long HOUR = TimeUnit.HOURS.toMillis(1);

     long DAY = TimeUnit.DAYS.toMillis(1);

}
