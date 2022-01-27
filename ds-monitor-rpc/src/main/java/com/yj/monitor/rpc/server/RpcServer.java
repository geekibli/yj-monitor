package com.yj.monitor.rpc.server;

import com.yj.monitor.rpc.config.RpcContainer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * @Author gaolei
 * @Date 2021/3/6 6:21 下午
 * @Version 1.0
 */
//@Configuration
public class RpcServer implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    @Value(value = "${rpc-port}")
    private Integer port;

    @Resource
    private RpcContainer rpcContainer;

    @Override
    public void afterPropertiesSet() {
        new Thread(() -> {
            start();
        }, "rpc-server-start-thread").start();
    }

    private void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) {
                        // 接受客户端请求的处理
                        ChannelPipeline pipeline = ch.pipeline();
                        //配置通用解码器
                        int fieldLength = 4;
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, fieldLength, 0, fieldLength));
                        pipeline.addLast(new LengthFieldPrepender(fieldLength));
                        pipeline.addLast("encoder", new ObjectEncoder());
                        pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                        pipeline.addLast(new RpcServerHandler(rpcContainer));
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture future = server.bind(this.port).sync();
            logger.info("Rpc registry started in port " + this.port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.info("Rpc server error! ", e);
            // TODO
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
