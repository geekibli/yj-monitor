package com.yj.monitor.admin.mapper;

import com.yj.monitor.admin.entity.MonitorMemoryPartition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitorMemoryPartitionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorMemoryPartition record);

    int insertSelective(MonitorMemoryPartition record);

    MonitorMemoryPartition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorMemoryPartition record);

    int updateByPrimaryKey(MonitorMemoryPartition record);
}