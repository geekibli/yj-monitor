package com.yj.monitor.admin.mapper.ext;

import com.yj.monitor.admin.entity.MonitorThread;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午3:42
 * @Version 1.0
 */
@Mapper
public interface MonitorThreadExtMapper {

    List<MonitorThread> getList(@Param("fromDate") Date fromDate,
                                @Param("toDate") Date toDate);


}
