package com.yj.monitor.core.handler;

import com.yj.monitor.api.constant.RemoteAPI;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午2:45
 * @Version 1.0
 */
@ChannelHandler.Sharable
@Component
public class ClientHeartBeatHandler extends SimpleChannelInboundHandler<String> {

    private final Logger logger = LoggerFactory.getLogger(ClientHeartBeatHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) {
        if (RemoteAPI.HEART_BEAT_MSG.equals(s)) {
            ctx.channel().writeAndFlush(RemoteAPI.HEART_BEAT_OK);
            return;
        }
        logger.info("heart beat : {}", s);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("【 " + ctx.channel().remoteAddress() + " 】is active");
    }


}
