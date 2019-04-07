package com.stude.javabase.concurrent;

/**
 * Created on 2019/4/2.
 *
 * @author Grak
 * @since 1.0
 */
public class Base02 {

    ThreadLocal<String> threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        Base02 base02 = new Base02();
        base02.threadLocal.set("大了要！");
        new Thread() {
            public void run() {
                base02.run01();
            }
        }.start();
        new Thread() {
            public void run() {
                base02.run02();
            }
        }.start();
        run03();
    }

    public synchronized void run01() {
        System.out.println("这是锁的对象类，this");
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }

    public void run02() {
        synchronized (this) {
            System.out.println("锁代码块");
            System.out.println(threadLocal.get());
        }
    }

    public static synchronized void run03() {
        System.out.println("锁静态方法，就成了锁.class文件，级别很高的锁，会锁得过多");
    }
}
