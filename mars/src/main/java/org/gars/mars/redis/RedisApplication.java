package org.gars.mars.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;

@SpringBootApplication
@ComponentScan({"org.gars.mars.redis"})
public class RedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(RedisApplication.class, args);

        RedisUtil propConf = (RedisUtil) ctx.getBean("redisUtil");
        propConf.set("zhaomuTest:" + 100001, "就是对的");

        propConf.hmset("zhaomuTest:" + 100006, new HashMap<>());

        String game = (String) propConf.get("zhaomuTest:" + 100001);
        HashMap name = (HashMap) propConf.hmget("zhaomuTest:" + 100006);
        System.out.println(name);
        ctx.close();


    }


}
