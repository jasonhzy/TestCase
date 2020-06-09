import designPattern.strategyFactory.LoginContextStrategyFactory;
import designPattern.strategyFactory.LoginService;
import designPattern.strategyFactory.LoginType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class TestClass {

    @Autowired
    private LoginContextStrategyFactory loginFactory;

    @Test
    public void testLogin(){ //使用方式如此（当前环境不能运行起来的）
        Map<String, String> params = new HashMap<>();
        LoginService loginService = loginFactory.doStrategy(LoginType.MOBILE);
        loginService.loginHandler(params);
    }
}
