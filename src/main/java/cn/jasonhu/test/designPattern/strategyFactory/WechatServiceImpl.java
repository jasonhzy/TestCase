package cn.jasonhu.test.designPattern.strategyFactory;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("WECHAT")
public class WechatServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Override
    public void loginHandler(Map<String, String> params) {
        System.out.println("wechat");
    }
}
