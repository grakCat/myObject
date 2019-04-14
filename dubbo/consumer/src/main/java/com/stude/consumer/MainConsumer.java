package com.stude.consumer;

import com.google.gson.Gson;
import com.stude.dubbo.dubbo.UserAddress;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created on 2019/4/14.
 *
 * @author Grak
 * @since 1.0
 */
public class MainConsumer {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        OrderServiceImpl orderServiceImpl = context.getBean(OrderServiceImpl.class);
        List<UserAddress> mm = orderServiceImpl.initOrder(3);
        System.out.println(new Gson().toJson(mm));
    }
}
