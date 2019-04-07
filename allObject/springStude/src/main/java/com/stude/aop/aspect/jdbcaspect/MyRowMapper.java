package com.stude.aop.aspect.jdbcaspect;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created on 2019/4/6.
 * 数据库字段和实际java对象转换
 * @author Grak
 * @since 1.0
 */
public class MyRowMapper implements RowMapper<Userinfo> {
    @Override
    public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        //使用的是数据库里面的名字
        int userId = rs.getInt("uid");
        String userName = rs.getString("username");
        String pwd = rs.getString("pwd");
        String userimg = rs.getString("userimg");
        Userinfo user = new Userinfo();
        user.setUid(userId);
        user.setUsername(userName);
        user.setPwd(pwd);
        user.setUserimg(userimg);
        return user;
    }
}
