package com.stude;

import com.stude.aop.aspect.UserXml;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:aop.xml")
@SpringBootApplication
public class QiaoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(QiaoApplication.class, args);

        //AopAspect执行
//        User myconf = (User) ctx.getBean("user");
//        myconf.add("test001");

        //AopXmlAspect执行
        UserXml user = (UserXml) ctx.getBean("userXml");
        user.add("sssss");

//        DemoAspect user = (DemoAspect) ctx.getBean("demoAspect");
//        user.runAspect();


    }
}
