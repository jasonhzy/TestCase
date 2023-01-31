package cn.jasonhu.learn.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jason hu
 * @since 2021/11/3 10:02
 */
@Aspect
@Component
public class LearnAspect {

    // 切入点
    @Pointcut("execution(* cn.jasonhu.impl.service.StudentService.*(..))")
    public void pointCut() {
    }

    // 前置通知
    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("前置通知，在方法执行前执行");
    }

    // 后置通知
    @After(value = "pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("后置通知，在方法执行后执行（无论是否发生异常）");
    }

    // 环绕通知
    @Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知之前");
        // 调用目标方法
        Object proceed = pjp.proceed();
        System.out.println("环绕通知之后");
        return proceed;
    }

    // 后置通知
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        System.out.println("返回通知，在方法返回结果之后执行");
    }

    // 异常通知
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("异常通知，在方法抛出异常后执行");
    }
}
