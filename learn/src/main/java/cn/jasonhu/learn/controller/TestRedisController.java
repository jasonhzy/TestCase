package cn.jasonhu.learn.controller;

import cn.jasonhu.commons.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jason hu
 * @since 2021/3/23 16:19
 */
@Api(tags = "测试Redis")
@RestController
@RequestMapping("/test/redis")
public class TestRedisController {

    @Autowired
    private RedisTemplate redisClient;


    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/hash")
    @ApiOperation(value = "redis hash")
    public ResponseResult setRedisHash() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jasonhu");
        map.put("age", 35);
        redisClient.opsForHash().putAll("user", map);
        return ResponseResult.success();
    }

    @GetMapping("/lock")
    @ApiOperation(value = "redis lock")
    public ResponseResult setRedissionLock() {
        RLock lock = this.redissonClient.getLock("testLock");
        if (lock.isLocked()) {
            return ResponseResult.fail();
        }
        lock.lock();
        try{
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return ResponseResult.success();
    }
}
