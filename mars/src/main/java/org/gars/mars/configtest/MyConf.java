package org.gars.mars.configtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/12/6.
 *
 * @author grayCat
 * @since 1.0
 */
@Component
public class MyConf {

    @Autowired
    private Environment env;

    public void show1() {
        //2
        System.out.println("===========================================");
        //获取字符串
        System.out.println("String: " + env.getProperty("string.port"));

        //获取整数
        System.out.println("Interger: " + (env.getProperty("integer.port", Integer.class)));
        System.out.println(env.getProperty("db.link.url"));
        System.out.println(env.getProperty("db.link.driver"));
        System.out.println(env.getProperty("db.link.username"));
        System.out.println(env.getProperty("db.link.password"));
        System.out.println("===========================================");
    }

    @Value("${string.port}")
    private int intPort;
    @Value("${string.port}")
    private String stringPort;
    @Value("${db.link.url}")
    private String dbUrl;
    @Value("${db.link.driver}")
    private String dbDriver;
    @Value("${db.link.username}")
    private String dbUsername;
    @Value("${db.link.password}")
    private String dbPassword;

    public void show2() {
        //3
        System.out.println("===========================================");
        System.out.println("intPort :" + (intPort));
        System.out.println("stringPort :" + (stringPort));
        System.out.println("string :   " + dbUrl);
        System.out.println("string :   " + dbDriver);
        System.out.println("string :   " + dbUsername);
        System.out.println("string :   " + dbPassword);
        System.out.println("===========================================");
    }
}
