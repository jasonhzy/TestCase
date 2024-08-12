package cn.jasonhu.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SpringContextUtils.class);

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        return (T)getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T)getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return (T)getApplicationContext().getBean(name, clazz);
    }

    public static <T> T getBeanIgnoreNotFound(String name) {
        T t = null;
        try {
            t = getBean(name);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {}
        return t;
    }

    public static <T> T getBeanIgnoreNotFound(Class<T> clazz) {
        T t = null;
        try {
            t = getBean(clazz);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {}
        return t;
    }

    public static <T> T getBeanIgnoreNotFound(String name, Class<T> clazz) {
        T t = null;
        try {
            t = getBean(name, clazz);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {}
        return t;
    }

    public static void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }
}