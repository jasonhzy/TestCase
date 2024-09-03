package cn.jasonhu.learn;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LearnApplication.class)
public class TestRedis {

    @Resource
    private StringRedisTemplate redisClient;

    @Test
    public void testHash() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jasonhu");
        map.put("age", 35);
        this.redisClient.opsForHash().putAll("user", map);
        System.out.println("redis hash success");
    }
}
