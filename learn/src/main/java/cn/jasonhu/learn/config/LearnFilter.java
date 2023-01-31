package cn.jasonhu.learn.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jason hu
 */
@WebFilter(urlPatterns = {"/api/*"}, filterName = "corsFilter")
public class LearnFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "authorization, authorizationToken, appCode, x-requested-with, Content-Type, Access-Token");
        response.setHeader("Access-Control-Expose-Headers", "*");

        // 针对req和resp统一设置字符编码
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        filterChain.doFilter(servletRequest, servletResponse); // 传递给下一个Filter进行处理
    }

    @Override
    public void destroy() {
    }
}

