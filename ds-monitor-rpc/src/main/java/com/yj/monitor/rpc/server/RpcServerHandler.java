package com.yj.monitor.rpc.server;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.rpc.config.RpcContainer;
import com.yj.monitor.rpc.domain.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @Author gaolei
 * @Date 2021/3/6 6:34 下午
 * @Version 1.0
 */
public class RpcServerHandler<T> extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(RpcServerHandler.class);

    private RpcContainer<T> rpcContainer;

    public RpcServerHandler(RpcContainer<T> rpcContainer) {
        this.rpcContainer = rpcContainer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result;
        RpcRequest request = (RpcRequest) msg;
        logger.info("request :{}", JSON.toJSONString(request));
        Object provider = rpcContainer.getInstance(request.getClassName());
        Method method = provider.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
        result = method.invoke(provider, request.getParameters());
        ctx.writeAndFlush(result);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.close();
    }


    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("Rpc server handler error! ", cause);
        ctx.close();
    }
}
