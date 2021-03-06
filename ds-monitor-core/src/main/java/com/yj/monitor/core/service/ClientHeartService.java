package com.yj.monitor.core.service;

import com.yj.monitor.api.constant.RemoteAPI;
import com.yj.monitor.core.config.MonitorConfig;
import com.yj.monitor.core.config.MonitorConfiguration;
import com.yj.monitor.core.handler.ClientHeartBeatHandler;
import com.yj.monitor.core.handler.SpringContextUtil;
import com.yj.monitor.core.runner.LoadRunner;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午3:38
 * @Version 1.0
 */
@Component
@Order
public class ClientHeartService {

    private final Logger logger = LoggerFactory.getLogger(ClientHeartService.class);

    @Resource
    private MonitorConfig monitorConfig;
    @Resource
    private StringEncoder stringEncoder;
    @Resource
    private StringDecoder stringDecoder;
    @Resource
    private ClientHeartBeatHandler clientHeartBeatHandler;


    public void sendHeart(String host, Integer port) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", stringDecoder);
                            pipeline.addLast("encoder", stringEncoder);
                            pipeline.addLast(clientHeartBeatHandler);
                        }
                    });

            logger.info("Send heart to admin for : {}", host + ":" + port);
            Channel channel = bootstrap.connect(host, port).sync().channel();
            while (channel.isActive()) {
                Thread.sleep(TimeUnit.SECONDS.toMillis(monitorConfig.getHeartInterval()));
                if (StringUtils.isBlank(LoadRunner.localAddress)) {
                    LoadRunner.localAddress = channel.localAddress().toString();
                }
                channel.writeAndFlush(RemoteAPI.HEART_BEAT_MSG + monitorConfig.getClientId());
            }
        } catch (Exception e) {
            logger.warn("Monitor client send heart error! ", e);
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
