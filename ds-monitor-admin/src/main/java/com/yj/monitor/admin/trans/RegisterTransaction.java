package com.yj.monitor.admin.trans;

import com.yj.monitor.admin.entity.ClientRegisterTable;
import com.yj.monitor.admin.mapper.ClientRegisterTableMapper;
import com.yj.monitor.admin.mapper.ext.ClientRegisterTableExtMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午5:37
 * @Version 1.0
 */
@Service
public class RegisterTransaction {

    @Resource
    private ClientRegisterTableMapper clientRegisterTableMapper;
    @Resource
    private ClientRegisterTableExtMapper clientRegisterTableExtMapper;

    @Transactional(rollbackFor = Exception.class)
    public synchronized void registerClient(String clientId, ClientRegisterTable table){
        ClientRegisterTable client = clientRegisterTableExtMapper.getByClientId(clientId);
        if (null == client) {
            clientRegisterTableMapper.insertSelective(table);
        }else {
            clientRegisterTableExtMapper.incrLoadTimes(clientId);
        }
    }

}
