package com.yj.monitor.admin.mapper;

import com.yj.monitor.admin.entity.MonitorServer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitorServerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorServer record);

    int insertSelective(MonitorServer record);

    MonitorServer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorServer record);

    int updateByPrimaryKey(MonitorServer record);
}