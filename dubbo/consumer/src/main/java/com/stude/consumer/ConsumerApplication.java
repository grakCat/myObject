package com.stude.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ConsumerApplication.class, args);
        OrderServiceImpl order = (OrderServiceImpl)ctx.getBean("orderServiceImpl");
        for(int i=0;i<10;i++){
            System.out.println( new Gson().toJson(order.initOrder(111)));
        }
    }
}
