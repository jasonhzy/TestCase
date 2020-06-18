package cn.jasonhu.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestCaseApplication {

    public static void main(String[] args) {
        SpringApplication application =  new SpringApplication(TestCaseApplication.class);
        application.run(args);
    }
}
