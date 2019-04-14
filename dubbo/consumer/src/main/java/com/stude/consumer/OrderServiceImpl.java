package com.stude.consumer;

import com.stude.dubbo.dubbo.UserAddress;
import com.stude.dubbo.dubbo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2019/4/14.
 *
 * @author Grak
 * @since 1.0
 */
@Service
public class OrderServiceImpl {

    @Autowired
    UserService userService;

    public List<UserAddress> initOrder(long userId) {
        return userService.getUserAddress(userId);
    }
}
