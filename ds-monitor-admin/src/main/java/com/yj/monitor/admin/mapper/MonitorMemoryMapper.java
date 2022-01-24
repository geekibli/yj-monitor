package com.yj.monitor.admin.mapper;

import com.yj.monitor.admin.entity.MonitorMemory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitorMemoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorMemory record);

    int insertSelective(MonitorMemory record);

    MonitorMemory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorMemory record);

    int updateByPrimaryKey(MonitorMemory record);
}