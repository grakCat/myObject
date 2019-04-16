package com.stude.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.stude.dubbo.dubbo.UserAddress;
import com.stude.dubbo.dubbo.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/4/15.
 *
 * @author Grak
 * @since 1.0
 */
@Service(version = "2.0.1")
@Component
public class UserServer007  implements UserService {

    @Override
    public List<UserAddress> getUserAddress(long playerId) {
        List<UserAddress> userAddresses = new ArrayList<>();
        for(int i = 0;i< 5;i++){
            UserAddress address = new UserAddress();
            address.setId(i + 1);
            address.setUserAddress("老大大大大" + (i + 3) * 7 +"号公路");
            address.setUserId("1111" + i);
            address.setConsignee("幻四");
            address.setPhoneNum((1111 * i) +"");
            address.setIsDefault("有多少事！！");
            userAddresses.add(address);
        }
        return userAddresses;
    }
}
