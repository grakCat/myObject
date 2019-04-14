package com.stude.provider;

import com.stude.dubbo.dubbo.UserAddress;
import com.stude.dubbo.dubbo.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/4/14.
 *
 * @author Grak
 * @since 1.0
 */
public class UserServiceImpl implements UserService {

    @Override
    public List<UserAddress> getUserAddress(long playerId) {
        List<UserAddress> userAddresses = new ArrayList<>();
        for(int i = 0;i< 5;i++){
            UserAddress address = new UserAddress();
            address.setId(i + 1);
            address.setUserAddress("秋名山" + (i + 3) * 7 +"号公路");
            address.setUserId("1111" + i);
            address.setConsignee("幻三");
            address.setPhoneNum((1111 * i) +"");
            address.setIsDefault("有多少事！！");
            userAddresses.add(address);
        }
        return userAddresses;
    }
}
