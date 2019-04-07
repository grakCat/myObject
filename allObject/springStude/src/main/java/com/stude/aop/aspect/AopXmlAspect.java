package com.stude.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created on 2019/4/5.
 *
 * @author Grak
 * @since 1.0
 */
public class AopXmlAspect {

    // 前置通知
    public void begin() {
        System.out.println("前置通知");
    }

    //
    // 后置通知
    public void commit() {
        System.out.println("后置通知");
    }

    // 运行通知
    public void returning() {
        System.out.println("运行通知");
    }

    // 异常通知
    public void afterThrowing() {
        System.out.println("异常通知");
    }

    // 环绕通知
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知开始");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知结束");
    }
}
