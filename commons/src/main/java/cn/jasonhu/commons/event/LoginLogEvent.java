package cn.jasonhu.commons.event;

import org.springframework.context.ApplicationEvent;
public class LoginLogEvent extends ApplicationEvent {

    private String username;

    private String loginType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public LoginLogEvent(Object source) {
        super(source);
    }
}