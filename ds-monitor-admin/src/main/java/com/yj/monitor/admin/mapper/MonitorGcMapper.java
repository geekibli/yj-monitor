package com.yj.monitor.admin.mapper;

import com.yj.monitor.admin.entity.MonitorGc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitorGcMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorGc record);

    int insertSelective(MonitorGc record);

    MonitorGc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorGc record);

    int updateByPrimaryKey(MonitorGc record);
}