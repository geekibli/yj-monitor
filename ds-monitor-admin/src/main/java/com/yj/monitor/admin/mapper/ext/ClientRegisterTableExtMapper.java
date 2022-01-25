package com.yj.monitor.admin.mapper.ext;

import com.yj.monitor.admin.entity.ClientRegisterTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午5:20
 * @Version 1.0
 */
@Mapper
public interface ClientRegisterTableExtMapper {

    ClientRegisterTable getByClientId(@Param("clientId") String clientId);

    void incrLoadTimes(@Param("clientId") String clientId);

    List<ClientRegisterTable> pageList(@Param("clientId") String clientId,
                                       @Param("applicationName") String applicationName);

     void setAddress(@Param("clientId") String clientId,
                           @Param("address") String address);

}
