package com.yj.monitor.admin.mapper;

import com.yj.monitor.admin.entity.ClientRegisterTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientRegisterTableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientRegisterTable record);

    int insertSelective(ClientRegisterTable record);

    ClientRegisterTable selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientRegisterTable record);

    int updateByPrimaryKey(ClientRegisterTable record);
}