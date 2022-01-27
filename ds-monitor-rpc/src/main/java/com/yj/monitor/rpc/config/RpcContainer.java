package com.yj.monitor.rpc.config;

import com.yj.monitor.rpc.anno.Rpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaolei
 */
@Component
public class RpcContainer<T> implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(RpcContainer.class);

    public final ConcurrentHashMap<String, T> RPC_CONTAINER = new ConcurrentHashMap<>();

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(Rpc.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String beanName = entry.getKey();
            Object bean = entry.getValue();
            if (bean == null) {
                continue;
            }
            Rpc annotation = applicationContext.findAnnotationOnBean(beanName, Rpc.class);
            T temp = (T) bean;
            Class<?>[] interfaces = bean.getClass().getInterfaces();
            if (interfaces == null || interfaces.length == 0) {
                logger.warn("[ " + beanName + " ] is marked with com.yj.monitor.anno.@Rpc but not implement rpc api!");
                continue;
            }
            RPC_CONTAINER.put(interfaces[0].getName(), temp);
        }
        logger.info(" Init rpc instance size : {}", RPC_CONTAINER.size());
    }


    public T getInstance(String rpcName) {
        return RPC_CONTAINER.get(rpcName);
    }


}
