package com.stude.aop.aspect;

import org.springframework.stereotype.Component;

/**
 * Created on 2019/4/5.
 *
 * @author Grak
 * @since 1.0
 */
@Component
public class UserXml {

    public void add(String message) {
        System.out.println("执行UserXml添加方法:" + message);
    }

}
