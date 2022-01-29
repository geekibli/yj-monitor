package com.yj.monitor.admin.mapper.ext;

import com.yj.monitor.admin.entity.MonitorServer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午4:24
 * @Version 1.0
 */
@Mapper
public interface MonitorServerExtMapper {

    List<MonitorServer> getList(@Param("fromDate") Date fromDate,
                                @Param("toDate") Date toDate,
                                @Param("clientId") String clientId);
}
