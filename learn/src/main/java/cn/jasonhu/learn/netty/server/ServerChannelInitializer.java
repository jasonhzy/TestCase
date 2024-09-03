package cn.jasonhu.learn.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) {
        // 解码编码
        sc.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
        sc.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));


        // ====================== 增加心跳支持 start    ====================
        sc.pipeline().addLast(new ServerIdleStateHandler());
        // 自定义的空闲状态检测
        sc.pipeline().addLast(new HeartBeatHandler());
        // ====================== 增加心跳支持 end    ======================

        // 自定义处理器
        sc.pipeline().addLast(new NettyServerHandler());
    }
}