package com.stude.qiao.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created on 2019/4/5.
 * jdk动态代理
 * 实现类，所以必须使用接口
 * 缺点：jdk动态代理，必须是面向接口，目标业务类必须实现接口
 *
 * @author Grak
 * @since 1.0
 */
public class JDKProxy implements InvocationHandler {

    // 这其实业务实现类对象，用来调用具体的业务方法
    private Object target;

    public JDKProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("调用开始处理");
        result = method.invoke(target, args);
        System.out.println("调用结束处理");
        return result;
    }

    public static void main(String[] args) {
        BaseSaticProxy.IUserDao userDao = new BaseSaticProxy.UserDao();// 被代理对象
        JDKProxy invocationHandlerImpl = new JDKProxy(userDao);//代理方法
        ClassLoader loader = userDao.getClass().getClassLoader();//类信息
        Class<?>[] interfaces = userDao.getClass().getInterfaces();//接口
        // 主要装载器、绑定类，接口，代理方法
        BaseSaticProxy.IUserDao newProxyInstance = (BaseSaticProxy.IUserDao) Proxy.newProxyInstance(loader, interfaces, invocationHandlerImpl);
        newProxyInstance.save();
    }
}
