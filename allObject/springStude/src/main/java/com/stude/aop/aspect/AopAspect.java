package com.stude.qiao.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created on 2019/4/5.
 * aop切面
 * （优先级3为执行逻辑代码）
 *
 * @author Grak
 * @since 1.0
 */
@Component
@Aspect
public class AopAspect {

    // 前置通知（优先级2）
    @Before("execution(* com.stude.qiao.aspect.User.add(..))")
    public void begin() {
        System.out.println("前置通知");
    }

    //
    // 后置通知（优先级5）
    @After("execution(* com.stude.qiao.aspect.User.add(..))")
    public void commit() {
        System.out.println("后置通知");
    }

    // 运行通知（优先级6）
    @AfterReturning("execution(* com.stude.qiao.aspect.User.add(..))")
    public void returning() {
        System.out.println("运行通知");
    }

    // 异常通知
    @AfterThrowing("execution(* com.stude.qiao.aspect.User.add(..))")
    public void afterThrowing() {
        System.out.println("异常通知");
    }

    // 环绕通知
    @Around("execution(* com.stude.qiao.aspect.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知开始");//(优先级1)
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕通知结束");//（优先级4）
    }
}
