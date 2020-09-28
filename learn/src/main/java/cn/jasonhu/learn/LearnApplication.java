package cn.jasonhu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude={ElasticsearchAutoConfiguration.class,ElasticsearchDataAutoConfiguration.class,
        ElasticsearchRepositoriesAutoConfiguration.class, JestAutoConfiguration.class, DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = { "cn.jasonhu.learn", "cn.jasonhu.jdbc"})
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication application =  new SpringApplication(LearnApplication.class);
        application.run(args);
    }
}

//springboot启动原理：https://www.jianshu.com/p/943650ab7dfd
