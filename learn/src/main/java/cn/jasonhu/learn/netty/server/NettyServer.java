package cn.jasonhu.learn.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Slf4j
@Component
public class NettyServer {

    @Value("${netty.socket.host}")
    private String host;

    @Value("${netty.socket.port}")
    private Integer port;

    // bossGroup 用于接收连接，workerGroup 用于具体的处理
    EventLoopGroup bossGroup = new NioEventLoopGroup(2);
    EventLoopGroup workGroup = new NioEventLoopGroup(2);

    /**
     * 启动Netty服务端
     *
     * 注解@PostConstruct和NettyApplication两种方式二选一均可启动
     */
    // @PostConstruct
    public void start() {
        log.info("NettyServer 启动...");

        InetSocketAddress address = new InetSocketAddress(host, port);
        // 创建服务端启动引导/辅助类：ServerBootstrap
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 给引导类配置两大线程组,确定了线程模型
        bootstrap.group(this.bossGroup, this.workGroup)
                // 指定Netty通道类型
                .channel(NioServerSocketChannel.class)
                // 服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 将小的数据包包装成更大的帧进行传送，提高网络的负载,即TCP延迟传输
                .option(ChannelOption.TCP_NODELAY, true)
                /**
                 * 有两种方式实现心跳机制：
                 * 1）使用TCP协议层面的keepalive机制，即childOption(ChannelOption.SO_KEEPALIVE, true)
                 * 2）在应用层上自定义的心跳机制
                 */
                // 设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                // .childOption(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                // 指定通道初始化器用来加载当Channel收到事件消息后
                .childHandler(new ServerChannelInitializer());
        try {
            log.info("服务器启动开始监听端口: {}", address.getPort());
            ChannelFuture f = bootstrap.bind(address).sync();
            if (f.isSuccess()) {
                log.info("启动Netty Server成功");
            } else {
                log.info("启动Netty Server失败");
            }
            // 等待服务器监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        log.info("Shutdown Netty Server...");
        this.bossGroup.shutdownGracefully();
        this.workGroup.shutdownGracefully();
        log.info("Shutdown Netty Server success");
    }
}