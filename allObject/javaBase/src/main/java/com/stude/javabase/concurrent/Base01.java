package com.stude.javabase.concurrent;

import com.stude.javabase.concurrent.create.ImplementsCreate;

/**
 * Created on 2019/4/7.
 * 线程
 * @author Grak
 * @since 1.0
 */
public class Base01 {

//    public static void main(String[] args) {
//        //继承Thread
//        new ExtendsCreate().start();
//        //实现Runable
//        new Thread(new ImplementsCreate()).start();
//        //匿名内部类
//        new Thread(){
//            public void run(){
//                System.out.println("匿名内部类");
//            }
//        }.start();
//
//
//    }

    public static void main(String[] args) {
        new Thread();//1
        new Thread("name");//2负值线程名字
        new Thread(new ImplementsCreate());//3实现了Runable接口的对象
        new Thread(new ImplementsCreate(),"name");//4
    }
}
