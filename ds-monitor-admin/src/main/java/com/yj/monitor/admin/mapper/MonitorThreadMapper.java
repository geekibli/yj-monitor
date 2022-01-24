package com.yj.monitor.admin.mapper;

import com.yj.monitor.admin.entity.MonitorThread;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitorThreadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorThread record);

    int insertSelective(MonitorThread record);

    MonitorThread selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorThread record);

    int updateByPrimaryKey(MonitorThread record);
}