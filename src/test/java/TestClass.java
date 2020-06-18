import cn.jasonhu.test.TestCaseApplication;
import cn.jasonhu.test.designPattern.strategyFactory.LoginContextStrategyFactory;
import cn.jasonhu.test.designPattern.strategyFactory.LoginService;
import cn.jasonhu.test.designPattern.strategyFactory.LoginType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestCaseApplication.class)
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
