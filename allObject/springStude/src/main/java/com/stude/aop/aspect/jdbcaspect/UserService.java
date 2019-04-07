package com.stude.aop.aspect.jdbcaspect;

import java.util.List;

/**
 * Created on 2019/4/6.
 *
 * @author Grak
 * @since 1.0
 */
public interface UserService {

    List<Userinfo> findAll();
    Userinfo findById(int userId);
    int create(String username,String pwd,String userimg);
    int update(String username,String pwd,String userimg, int userId);
    int delete(int userId);
}
