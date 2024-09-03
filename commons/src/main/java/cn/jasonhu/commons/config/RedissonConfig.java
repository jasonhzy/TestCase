package cn.jasonhu.commons.config;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.List;

@Configuration
public class RedissonConfig {
    private static final Logger log = LoggerFactory.getLogger(RedissonConfig.class);

    @Value("${spring.redis.mode:single}")
    private String redisMode;

    @Autowired
    private RedisProperties redisProperties;

    private static final String REDIS_PROTOCOL_PREFIX = "redis://";

    @Bean
    public RedissonClient redissonClient() {
        switch (redisMode) {
            case "cluster":
                return redissonCluster();
            case "sentinel":
                return redissonSentinel();
            case "single":
            default:
                return redissonSingle();
        }
    }

    /**
     * 单机模式
     */
    private RedissonClient redissonSingle() {
        Config config = new Config();
        String redisAddress = String.format(REDIS_PROTOCOL_PREFIX + "%s:%d", new Object[] { this.redisProperties.getHost(), Integer.valueOf(this.redisProperties.getPort()) });
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(redisAddress);
        if (StringUtil.isNotBlank(this.redisProperties.getPassword()))
            singleServerConfig.setPassword(this.redisProperties.getPassword());
        config.setNettyThreads(2);
        config.setThreads(2);
        log.info(String.format("=====>Build redisson obj,config: %s %s", new Object[] { redisAddress, this.redisProperties.getPassword() }));
        return Redisson.create(config);
    }

    /**
     * 集群模式
     */
    private RedissonClient redissonCluster() {
        // 192.168.116.156:1901,192.168.116.156:1902
        List<String> nodes = redisProperties.getCluster().getNodes();
        String password = redisProperties.getPassword();
        // 声明一个配置类
        Config config = new Config();
        ClusterServersConfig clusterServersConfig = config.useClusterServers();
        // 扫描间隔
        clusterServersConfig.setScanInterval(2000);
        // 判断密码
        if (!StringUtils.isEmpty(password)) {
            clusterServersConfig.setPassword(password);
        }
        // 添加redis节点
        for (String node : nodes) {
            clusterServersConfig.addNodeAddress(REDIS_PROTOCOL_PREFIX + node);
        }
        return Redisson.create(config);
    }

    /**
     * 哨兵模式
     */
    private RedissonClient redissonSentinel() {
        // mymaster
        String masterName = redisProperties.getSentinel().getMaster();
        String nodes = redisProperties.getSentinel().getNodes();
        String password = redisProperties.getPassword();
        // 声明一个配置类
        Config config = new Config();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
        // 扫描间隔
        sentinelServersConfig.setScanInterval(2000);
        // 判断密码
        if (!StringUtils.isEmpty(password)) {
            sentinelServersConfig.setPassword(password);
        }
        sentinelServersConfig.setMasterName(masterName);
        String[] nodeArr = StringUtils.split(nodes, ",");
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = REDIS_PROTOCOL_PREFIX + nodeArr[i];
        }
        // 添加redis节点
        sentinelServersConfig.addSentinelAddress(nodeArr);
        return Redisson.create(config);
    }
}