package com.stude.javabase.concurrent;

/**
 * Created on 2019/4/2.
 *
 * @author Grak
 * @since 1.0
 */
public class Base01 {

    public static void main(String[] args) {
        Thread thread02 = new Thread(new Thread02());
        Thread thread01 = new Thread01(thread02);

        thread01.start();
        thread02.start();
        try {
            thread02.yield();
            thread02.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //new线程的四种传值方式
        new Thread();
        new Thread("name");
        new Thread(new Thread02());
        new Thread(new Thread02(), "name");
    }

    public static class Thread01 extends Thread {

        Thread thread02;

        Thread01(Thread thread02) {
            this.thread02 = thread02;
        }


        public void run() {
            System.out.println("执行继承线程");
            try {
                thread02.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程id:" + getId());
            Thread thread = currentThread();//获取当前线程对象
            System.out.println("线程name:" + getName());
            //守护线程
//            setDaemon(true);
        }
    }

    public static class Thread02 implements Runnable {

        @Override
        public void run() {
            System.out.println("执行实现线程");

            new Thread() {
                public void run() {
                    System.out.println("执行内部类线程");
                }
            }.start();
        }
    }


}
