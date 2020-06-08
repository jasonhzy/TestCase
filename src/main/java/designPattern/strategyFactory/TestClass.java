package designPattern.strategyFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class TestClass {

    @Autowired
    private LoginContextStrategyFactory loginFactory;

    @Test
    public void testLogin(){
        Map<String, String> params = new HashMap<>();

        LoginService loginService = loginFactory.doStrategy(LoginType.MOBILE);
        loginService.loginHandler(params);
    }
}
