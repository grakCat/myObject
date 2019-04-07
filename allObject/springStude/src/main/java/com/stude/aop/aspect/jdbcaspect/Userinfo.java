package com.stude.aop.aspect.jdbcaspect;

/**
 * Created on 2019/4/6.
 *
 * @author Grak
 * @since 1.0
 */
public class Userinfo {

    private int uid;

    private String username;

    private String pwd;

    private String userimg;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", userimg='" + userimg + '\'' +
                '}';
    }
}
