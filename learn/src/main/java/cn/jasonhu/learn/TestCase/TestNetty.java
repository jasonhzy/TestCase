package cn.jasonhu.learn.TestCase;

import cn.jasonhu.learn.netty.client.NettyClient;

public class TestNetty {

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        client.sendMsg("hello jasonhu");
    }
}
