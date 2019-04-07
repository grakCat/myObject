package com.stude.qiao.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created on 2019/4/6.
 *
 * @author Grak
 * @since 1.0
 */
@Component
@Aspect
public class AopSpringAspect {

    @Autowired
    private TransactionUtils transactionUtils;

    // // 异常通知
    @AfterThrowing("execution(* com.stude.qiao.aspect.jdbcaspect.DemoAspect.runAspect(..))")
    public void afterThrowing() {
        System.out.println("程序已经回滚");
        // 获取程序当前事务 进行回滚
        transactionUtils.rollback(TransactionAspectSupport.currentTransactionStatus());
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    // 环绕通知
    @Around("execution(* com.stude.qiao.aspect.jdbcaspect.DemoAspect.runAspect(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("开启事务");
        TransactionStatus begin = transactionUtils.begin();
        proceedingJoinPoint.proceed();
        transactionUtils.commit(begin);
        System.out.println("提交事务");
    }
}
