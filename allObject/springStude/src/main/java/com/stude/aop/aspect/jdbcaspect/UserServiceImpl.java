package com.stude.aop.aspect.jdbcaspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2019/4/6.
 *
 * @author Grak
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Userinfo> findAll() {
        String sql = "select * from userinfo ";
        return this.jdbcTemplate.query(sql,new MyRowMapper());
    }

    @Override
    public Userinfo findById(int userId) {
        String sql = "select * from userinfo where uid = ?";
        Userinfo user = jdbcTemplate.queryForObject(sql,new MyRowMapper(),userId);
        return user;
    }

    @Override
    public int create(String username, String pwd, String userimg) {
        String sql = "insert into userinfo(username,pwd,userimg) values(?,?,?)";
        return jdbcTemplate.update(sql,username,pwd,userimg);
    }

    @Override
    public int update(String username, String pwd, String userimg, int userId) {
        String sql = "update userinfo set username = ? , pwd = ? , userimg = ? where uid = ?";
        return jdbcTemplate.update(sql, username,pwd,userimg,userId);
    }

    @Override
    public int delete(int userId) {
        String sql = "delete from userinfo where uid = ?";
        return jdbcTemplate.update(sql,userId);
    }
}
