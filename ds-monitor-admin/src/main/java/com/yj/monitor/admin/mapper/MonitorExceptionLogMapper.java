package com.yj.monitor.admin.mapper;

import com.yj.monitor.admin.entity.MonitorExceptionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitorExceptionLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorExceptionLog record);

    int insertSelective(MonitorExceptionLog record);

    MonitorExceptionLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorExceptionLog record);

    int updateByPrimaryKey(MonitorExceptionLog record);
}