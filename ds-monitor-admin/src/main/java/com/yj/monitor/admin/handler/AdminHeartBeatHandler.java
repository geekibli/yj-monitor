package com.yj.monitor.admin.handler;

import com.yj.monitor.admin.domain.RegisterCenter;
import com.yj.monitor.admin.service.RegisterService;
import com.yj.monitor.api.constant.RemoteAPI;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.concurrent.atomic.LongAdder;

import static io.netty.handler.timeout.IdleState.*;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午2:45
 * @Version 1.0
 */
@ChannelHandler.Sharable
@Component
public class AdminHeartBeatHandler extends SimpleChannelInboundHandler<String> {

    private final Logger logger = LoggerFactory.getLogger(AdminHeartBeatHandler.class);

    LongAdder readIdleTimes = new LongAdder();


    @Resource
    private RegisterService registerService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) {
        try {
            if (s.startsWith(RemoteAPI.HEART_BEAT_MSG)) {
                RegisterCenter.markAddress(ctx.channel().remoteAddress().toString(), s.replace(RemoteAPI.HEART_BEAT_MSG, ""));
                registerService.setAddress(s.replace(RemoteAPI.HEART_BEAT_MSG, ""), ctx.channel().remoteAddress().toString());
                ctx.channel().writeAndFlush(RemoteAPI.HEART_BEAT_OK);
                return;
            }
            logger.info(" read msg :{}", s);
        } catch (Exception e) {
            logger.info("channel read error", e);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        IdleStateEvent event = (IdleStateEvent) evt;
        String eventType = "";
        switch (event.state()) {
            case READER_IDLE:
                eventType = READER_IDLE.name();
                readIdleTimes.increment();
                break;
            case WRITER_IDLE:
                eventType = WRITER_IDLE.name();
                break;
            case ALL_IDLE:
                eventType = ALL_IDLE.name();
            default:
                break;
        }

        logger.info(ctx.channel().remoteAddress() + " timeout event : " + eventType);
        if (readIdleTimes.sum() > 10) {
            logger.warn(" Heartbeat timeout limit exceeded ");
            RegisterCenter.removeByAddress(ctx.channel().remoteAddress().toString());
            ctx.channel().writeAndFlush("idle close");
            ctx.channel().close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("【 " + ctx.channel().remoteAddress() + " 】is active");

    }
}
