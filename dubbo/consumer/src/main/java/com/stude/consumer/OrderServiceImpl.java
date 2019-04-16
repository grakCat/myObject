package com.stude.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stude.dubbo.dubbo.UserAddress;
import com.stude.dubbo.dubbo.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2019/4/14.
 *
 * @author Grak
 * @since 1.0
 */
@Service //这个是spring的注解
public class OrderServiceImpl {

    @Reference(version = "2.0.1")
    UserService userService;

    public List<UserAddress> initOrder(long userId) {
        return userService.getUserAddress(userId);
    }
}
