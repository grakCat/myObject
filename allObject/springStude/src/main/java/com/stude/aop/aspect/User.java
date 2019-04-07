package com.stude.aop.aspect;

import org.springframework.stereotype.Component;

/**
 * Created on 2019/4/5.
 *
 * @author Grak
 * @since 1.0
 */
@Component
public class User {

    public void add(String message) {
        System.out.println("执行User添加方法:" + message);
    }

    public void addNum(String message, int num) {
        System.out.println("执行User添加方法:" + message + "，编号：" + num);
    }
}
