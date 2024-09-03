package cn.jasonhu.learn.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ServerIdleStateHandler extends IdleStateHandler {
    /**
     * 设置空闲检测时间为 30s
     */
    private static final int ALL_IDLE_TIME = 30;

    // ====================== 增加心跳支持 start    ======================
    // 针对客户端，如果在30秒没有向服务端发送读写心跳(ALL)，则主动断开
    // 如果是读空闲或者写空闲，不处理,读写空闲超过30秒，则断开连接
    public ServerIdleStateHandler() {
        super(0, 0, ALL_IDLE_TIME, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.info("{} 秒内没有读写数据,关闭连接", ALL_IDLE_TIME);
        ctx.channel().close();
    }
}
