package com.yj.monitor.admin.service;

import com.yj.monitor.admin.config.AdminMonitorConfig;
import com.yj.monitor.admin.domain.ClientContainer;
import com.yj.monitor.admin.handler.AdminHeartBeatHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午2:51
 * @Version 1.0
 */
@Service
public class HeartBeatService {

    private final Logger logger = LoggerFactory.getLogger(HeartBeatService.class);

    @Resource
    private AdminMonitorConfig adminMonitorConfig;

    public void heartListen() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new IdleStateHandler(adminMonitorConfig.getHeart().getReaderIdleTime(),
                                    adminMonitorConfig.getHeart().getWriterIdleTime(),
                                    adminMonitorConfig.getHeart().getAllIdleTime(), TimeUnit.SECONDS));
                            pipeline.addLast(new AdminHeartBeatHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(adminMonitorConfig.getHeart().getPort()).sync();
            logger.info("Starting service [Monitor Admin]");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("Starting service [Monitor Admin] error!", e);
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
            ClientContainer.clear();
        }
    }

}
