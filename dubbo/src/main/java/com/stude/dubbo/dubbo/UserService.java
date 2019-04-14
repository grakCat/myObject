package com.stude.dubbo.dubbo;


import java.util.List;

/**
 * Created on 2019/4/14.
 *
 * @author Grak
 * @since 1.0
 */
public interface UserService {

    List<UserAddress> getUserAddress(long playerId);
}
