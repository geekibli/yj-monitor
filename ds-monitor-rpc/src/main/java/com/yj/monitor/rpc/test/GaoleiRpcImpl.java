package com.yj.monitor.rpc.test;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * @Author gaolei
 * @Date 2022/1/26 下午3:50
 * @Version 1.0
 */
public class GaoleiRpcImpl implements GaoleiRpc {

    @Override
    public Person getPerson(String name) {
        System.err.println("调用真正的方法了。。。。。。");
        return new Person(20, name);
    }
}
