package com.stude.aop.aspect;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created on 2019/4/5.
 * CGLIB动态代理
 * 不要求委托类必须实现接口，底层采用asm字节码生成框架生成代理类的字节码
 *
 * @author Grak
 * @since 1.0
 */
public class CGLIBProxy implements MethodInterceptor {

    private Object targetObject;

    public Object getInstance(Object target) {
        // 设置需要创建子类的类
        this.targetObject = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启事物");
        Object result = methodProxy.invoke(targetObject, objects);
        System.out.println("关闭事物");
        // 返回代理对象
        return result;
    }

    public static void main(String[] args) {
        CGLIBProxy cglibProxy = new CGLIBProxy();
        BaseSaticProxy.IUserDao userDao = (BaseSaticProxy.IUserDao) cglibProxy.getInstance(new BaseSaticProxy.UserDao());
        userDao.save();
    }

}
