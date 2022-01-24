package com.yj.monitor.admin.mapper;

import com.yj.monitor.admin.entity.MonitorClassLoad;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitorClassLoadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorClassLoad record);

    int insertSelective(MonitorClassLoad record);

    MonitorClassLoad selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorClassLoad record);

    int updateByPrimaryKey(MonitorClassLoad record);
}