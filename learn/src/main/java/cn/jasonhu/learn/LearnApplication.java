package cn.jasonhu.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class,
        ElasticsearchDataAutoConfiguration.class,
        ElasticsearchRepositoriesAutoConfiguration.class, JestAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"cn.jasonhu.learn", "cn.jasonhu.jdbc", "cn.jasonhu.impl", "cn.jasonhu.commons"})
@MapperScan({"cn.jasonhu.impl.mapper"})
@EnableScheduling
@EnableAsync
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LearnApplication.class);
        application.run(args);
    }
}

//springboot启动原理：https://www.jianshu.com/p/943650ab7dfd
