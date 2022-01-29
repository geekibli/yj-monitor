package com.yj.monitor.admin.mapper.ext;

import com.yj.monitor.admin.entity.MonitorExceptionLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gaolei
 * @Date 2022/1/29 下午2:23
 * @Version 1.0
 */
@Mapper
public interface MonitorExceptionLogExtMapper {

    List<MonitorExceptionLog> pageList(@Param("className") String className,
                                       @Param("methodName") String methodName);
}
