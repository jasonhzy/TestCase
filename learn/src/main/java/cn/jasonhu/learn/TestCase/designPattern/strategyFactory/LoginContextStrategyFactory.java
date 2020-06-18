package cn.jasonhu.learn.TestCase.designPattern.strategyFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginContextStrategyFactory {

    @Autowired
    private Map<String, LoginService> contextStrategyMap = new ConcurrentHashMap<>();

    public LoginService doStrategy(LoginType loginType) {
        return this.contextStrategyMap.get(loginType.name());
    }
}
