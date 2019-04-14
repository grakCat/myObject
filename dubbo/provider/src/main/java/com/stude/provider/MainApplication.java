package com.stude.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created on 2019/4/14.
 *
 * @author Grak
 * @since 1.0
 */
public class MainApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ioc =  new ClassPathXmlApplicationContext("provider.xml");
        ioc.start();
        //阻塞
        System.in.read();
    }
}
