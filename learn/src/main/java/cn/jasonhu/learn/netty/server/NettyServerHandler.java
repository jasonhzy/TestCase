package cn.jasonhu.learn.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAdded>>>channelId：{}", ctx.channel().id().asLongText());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端连接建立成功
        log.info("channelActive>>>客户端连接建立成功, clientId: {}", ctx.channel().remoteAddress());
    }

    // 服务器端读取到 客户端发送过来的数据，然后通过 Channel 回写数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        log.info("channelRead>>>服务端读取到从客户端: {}, 发送过来的数据: {}", ctx.channel().remoteAddress(), o.toString());
        // 回复客户端
        ctx.channel().writeAndFlush("netty server received success");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("channelInactive>>>客户端断开连接, clientId: {}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 打印异常
        log.info("异常>>>" + cause.getMessage());
        // 关闭连接
        ctx.close();
    }
}
