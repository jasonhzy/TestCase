package cn.jasonhu.learn;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@ComponentScan(basePackages = {"cn.jasonhu.commons", "cn.jasonhu.jdbc", "cn.jasonhu.impl", "cn.jasonhu.learn"})
@MapperScan({"cn.jasonhu.impl.mapper"})
@EnableScheduling
@EnableAsync
@EnableMethodCache(basePackages = { "cn.jasonhu.learn" })
@EnableCreateCacheAnnotation
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LearnApplication.class);
        application.run(args);
    }
}

//springboot启动原理：https://www.jianshu.com/p/943650ab7dfd
