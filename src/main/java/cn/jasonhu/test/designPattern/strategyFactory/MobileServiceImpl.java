package cn.jasonhu.test.designPattern.strategyFactory;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service("MOBILE")
public class MobileServiceImpl implements LoginService {

    @Override
    public void loginHandler(Map<String, String> params){
        System.out.println("mobile");
    }
}
