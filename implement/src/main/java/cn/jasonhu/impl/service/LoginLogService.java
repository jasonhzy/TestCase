package cn.jasonhu.impl.service;

import cn.jasonhu.commons.event.LoginLogEvent;
import cn.jasonhu.commons.utils.SpringContextUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LoginLogService {

    @Async
    @EventListener
    public void saveLoginLog(LoginLogEvent event) {
        try{
            Thread.sleep(5000);
            System.out.println("====>"+ event.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveLog() {
        LoginLogEvent log = new LoginLogEvent(this);
        log.setUsername("jasonhzy");
//        applicationContext.publishEvent((LoginLogEvent)log);
        SpringContextUtils.publishEvent(log);
        System.out.println("=========");
    }
}
