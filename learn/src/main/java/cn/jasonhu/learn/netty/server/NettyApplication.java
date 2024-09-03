package cn.jasonhu.learn.netty.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NettyApplication implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void run(String... args) throws Exception {
        this.nettyServer.start();
    }
}
