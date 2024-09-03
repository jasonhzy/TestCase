package cn.jasonhu.learn.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("client channelActive successfully！！！");
        ctx.channel().writeAndFlush("client success");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("接收服务端发送过来的消息：" + msg);
        // ctx.channel().writeAndFlush("客户端writeAndFlush：我是客户端");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端断开连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
