package com.yj.monitor.rpc.client;

import com.yj.monitor.rpc.domain.RpcRequest;
import com.yj.monitor.rpc.domain.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @Author gaolei
 * @Date 2021/3/6 6:58 下午
 * @Version 1.0
 */

public class RpcClient {

    private final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    private final String serverAddress;

    public RpcClient(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public <T> T create(Class<?> clazz) {
        MethodProxy proxy = new MethodProxy(clazz);
        Class<?>[] interfaces = clazz.isInterface() ?
                new Class[]{clazz} :
                clazz.getInterfaces();
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, proxy);
        return result;
    }

    public class MethodProxy implements InvocationHandler {
        private final Logger logger = LoggerFactory.getLogger(MethodProxy.class);
        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                } else {
                    return rpcInvoke(proxy, method, args);
                }
            } catch (Exception e) {
                logger.error("Proxy rpc client invoke error", e);
                return new RpcResponse("Proxy rpc client invoke error", e);
            }
        }

        private Object rpcInvoke(Object proxy, Method method, Object[] args) {
            //封装请求的内容
            RpcRequest msg = new RpcRequest();
            msg.setClassName(this.clazz.getName());
            msg.setMethodName(method.getName());
            msg.setParameters(args);
            msg.setParameterTypes(method.getParameterTypes());
            msg.setAccessToken("gaoleif1e629");
            msg.setTraceId(UUID.randomUUID().toString());
            msg.setCreateMillisTime(System.currentTimeMillis());

            final RpcProxyHandler consumerHandler = new RpcProxyHandler();
            EventLoopGroup group = new NioEventLoopGroup();

            try {
                Bootstrap client = new Bootstrap();
                client.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer() {
                            @Override
                            protected void initChannel(Channel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                int fieldLength = 4;
                                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, fieldLength, 0, fieldLength));
                                pipeline.addLast(new LengthFieldPrepender(fieldLength));
                                pipeline.addLast("encoder", new ObjectEncoder());
                                pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                                pipeline.addLast("handler", consumerHandler);
                            }
                        })
                        .option(ChannelOption.TCP_NODELAY, true);
                ChannelFuture future = client.connect(getServerHost(), getServerPort()).sync();
                future.channel().writeAndFlush(msg).sync();
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }

            return consumerHandler.getResponse();
        }

        private String getServerHost() {
            String[] split = serverAddress.split("/");
            String s = split[2];
            return s.split(":")[0];
        }

        private Integer getServerPort() {
            String[] split = serverAddress.split("/");
            String s = split[2];
            return Integer.parseInt(s.split(":")[1]);
        }
    }


}

