package cn.jasonhu.test;

import java.util.Set;
import java.util.HashSet;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class Sentinel {
    public static void main(String[] args) throws Exception {
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("123.57.81.91:4141");
        sentinels.add("123.57.81.91:4545");

        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
        System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
        Jedis master = sentinelPool.getResource();

        System.out.println("name: " + master.get("name"));
        master.close();
        sentinelPool.destroy();
    }
}