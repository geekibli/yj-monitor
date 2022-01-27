package com.yj.monitor.rpc.test;

import com.yj.monitor.rpc.client.RpcClient;

/**
 * @Author gaolei
 * @Date 2022/1/26 下午8:25
 * @Version 1.0
 */
public class ClientTest {

    public static void main(String[] args) {

        new RpcClient("http://localhost:9050").create(GaoleiRpc.class);

    }
}
