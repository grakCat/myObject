package com.stude.aop.note.base;

import com.google.gson.Gson;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2019/4/6.
 * 内置注解
 * @author Grak
 * @since 1.0
 */
public class BuiltIn {

    @Deprecated//使用过时方法
    @SuppressWarnings({ "all" })//取消警告显示
    @Override//重写父类
    public String toString() {
        //过时方法
        new Date().parse("");
        java.util.List list = new ArrayList();
        return null;
    }

    @AddAnnotation(userId = 66,userName = "诗仙",arrays = {"红","绿","白"})
    public void add(){

    }

    public static void main(String[] args) throws ClassNotFoundException {
        //反射获取类里面的所有方法
        Class<?> forName = Class.forName("com.stude.aop.note.base.BuiltIn");
        Method[] dec = forName.getDeclaredMethods();
        for(Method method : dec){
            //找到标记有AddAnnotation的方法
            AddAnnotation addAnnotation = method.getDeclaredAnnotation(AddAnnotation.class);
            if(addAnnotation != null){
                //打印AddAnnotation里的值
                System.out.println("userId:" + addAnnotation.userId());
                System.out.println("userName:" + addAnnotation.userName());
                System.out.println("arrays:" + new Gson().toJson(addAnnotation.arrays()));
            }
        }
    }
}
