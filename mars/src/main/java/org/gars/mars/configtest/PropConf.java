package org.gars.mars.configtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created on 2018/12/6.
 *
 * @author grayCat
 * @since 1.0
 * classpath:config/my.properties 是指resources里面config下
 */
@Configuration
@PropertySource("file:${user.dir}/config/my.properties")
public class PropConf {

    @Value("${aaa.a}")
    private String a;
    @Value("${aaa.b}")
    private String b;
    @Value("${aaa.c}")
    private String c;


    public void show() {
        System.out.println("a --- > " + a);
        System.out.println("b --- > " + b);
        System.out.println("c --- > " + c);
    }

}
