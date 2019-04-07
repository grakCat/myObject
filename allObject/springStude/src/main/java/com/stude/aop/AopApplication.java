package com.stude.aop;

import com.stude.aop.aspect.jdbcaspect.DemoAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@ImportResource("classpath:aop.xml")
@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AopApplication.class, args);

        //AopAspect执行
//        User myconf = (User) ctx.getBean("user");
//        myconf.add("test001");

        //AopXmlAspect执行
//        UserXml user = (UserXml) ctx.getBean("userXml");
//        user.add("sssss");

        DemoAspect user = (DemoAspect) ctx.getBean("demoAspect");
        user.runAspect();


    }
}
