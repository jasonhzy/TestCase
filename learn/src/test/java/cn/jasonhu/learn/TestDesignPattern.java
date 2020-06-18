package cn.jasonhu.learn;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.jasonhu.learn.TestCase.designPattern.strategyFactory.LoginContextStrategyFactory;
import cn.jasonhu.learn.TestCase.designPattern.strategyFactory.LoginService;
import cn.jasonhu.learn.TestCase.designPattern.strategyFactory.LoginType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LearnApplication.class)
public class TestDesignPattern {

    @Autowired
    private LoginContextStrategyFactory loginFactory;

    @Test
    public void testLogin() {
        Map<String, String> params = new HashMap<>();
        LoginService loginService = loginFactory.doStrategy(LoginType.MOBILE);
        loginService.loginHandler(params);
    }
}
