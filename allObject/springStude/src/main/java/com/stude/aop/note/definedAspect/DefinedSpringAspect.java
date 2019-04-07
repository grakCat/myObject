package com.stude.aop.note.definedAspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * Created on 2019/4/6.
 *
 * @author Grak
 * @since 1.0
 */
@Component
@Aspect
public class DefinedSpringAspect {

    @Autowired
    private DefinedTransactionUtils transactionUtils;

    // // 异常通知
    @AfterThrowing(throwing="ex",pointcut = "execution(* com.stude.aop.note.*.*(..))")
    public void afterThrowing(Throwable ex) {
        System.out.println("程序已经回滚");
        //普通没添加事物的  异常也会执行，，，，垃圾方法

        // 获取程序当前事务 进行回滚
        transactionUtils.rollback();
    }

    // 环绕通知
    @Around("execution(* com.stude.aop.note.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        TransactionStatus begin = begin(proceedingJoinPoint);
        proceedingJoinPoint.proceed();
        commit(begin);
    }

    private TransactionStatus begin(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        // // 判断是否有自定义事务注解
        ExtTransaction declaredAnnotation = getExtTransaction(proceedingJoinPoint);
        if (declaredAnnotation == null) {
            return null;
        }
        // 如果有自定义事务注解，开启事务
        System.out.println("开启事务");
        return transactionUtils.begin();
    }

    private void commit(TransactionStatus transactionStatu){
        if (transactionStatu != null) {
            // 提交事务
            System.out.println("提交事务");
            transactionUtils.commit(transactionStatu);
        }
    }

    private ExtTransaction getExtTransaction(ProceedingJoinPoint pjp) throws NoSuchMethodException, SecurityException {
        // 获取方法名称
        String methodName = pjp.getSignature().getName();
        // 获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();
        // 获取目标对象类型
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        // 获取目标对象方法
        Method objMethod = classTarget.getMethod(methodName, par);
        // // 判断是否有自定义事务注解
        ExtTransaction declaredAnnotation = objMethod.getDeclaredAnnotation(ExtTransaction.class);
        if (declaredAnnotation == null) {
            System.out.println("您的方法上,没有加入注解!");
            return null;
        }
        return declaredAnnotation;

    }
}
