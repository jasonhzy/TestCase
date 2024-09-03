package cn.jasonhu.learn.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NettyClient {

    private final EventLoopGroup group = new NioEventLoopGroup();

    public void start(String host, Integer port) {
        /**
         * Bootstrap 是一个启动NIO服务的辅助启动类 客户端的
         */
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientChannelInitializer());
        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                log.info("netty client conn success");
                future.channel().writeAndFlush("client msg");
            }
            future.channel().closeFuture().sync();
        } catch ( InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        // sync() 和 addListener两种方式
        // ChannelFuture future = bootstrap.connect(host, port);
        // //客户端断线重连逻辑
        // future.addListener((ChannelFutureListener) f -> {
        //     if (f.isSuccess()) {
        //         log.info("连接Netty服务端成功");
        //     } else {
        //         log.info("连接失败，进行断线重连");
        //         f.channel().eventLoop().schedule(() -> start("127.0.0.1", 8888), 20, TimeUnit.SECONDS);
        //     }
        // });
        // ChannelFuture closeFuture = future.channel().closeFuture();
        // closeFuture.addListener((ChannelFutureListener) f -> {
        //     log.debug("处理 channel 关闭之后的操作");
        //     group.shutdownGracefully();
        // });
    }

    public void sendMsg(Object msg) {
        try {
            NettyClient client = new NettyClient();
            client.start("127.0.0.1", 8888);
        } catch (Exception e) {
            log.error("send message error: {}", e);
        }
    }



    @PreDestroy
    public void destroy() {
        group.shutdownGracefully();
    }
}
