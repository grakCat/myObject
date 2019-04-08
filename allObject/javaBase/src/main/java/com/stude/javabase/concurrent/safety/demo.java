package com.stude.javabase.concurrent.safety;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2019/4/8.
 *
 * @author hy
 * @since 1.0
 */
public class demo {

    private Object lock = new Object();

    /**
     * 修饰需要进行同步的方法，此时锁的对象为this
     */
    public synchronized void getId(){
        System.out.println("getId只允许一次被一个线程访问");
    }

    /**
     * 同步代码块，锁的粒度可以更细
     * 并且充当锁的对象不一定是this，也可以是其它对象，所以使用起来更加灵活
     */
    public void getName(){
        synchronized (lock){
            System.out.println("getName只允许一次被一个线程访问");
        }
    }

    public void runBase(){
        Object lock = new Object();
        new Thread(){
            public void run(){
                synchronized (lock){
                    try {
                        lock.wait();
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
             }
        }.start();
        new Thread(){
            public void run(){
                synchronized (lock){
                    lock.notify();
                }
            }
        }.start();
    }

    public void run02(){
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(){
            public void run(){
                try {
                    lock.lock();
                    condition.await();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    lock.lock();
                    condition.signal();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }.start();
    }

    public void atomicRun(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        if(atomicBoolean.compareAndSet(true,false)){
            System.out.println("true就执行，并同时把值改为false");
        }
    }
//
//    public static void main(String[] args) {
//        CountDownLatch countDownLatch = new CountDownLatch(4);
//        new Thread(new Runnable() {
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + ",子线程开始执行...");
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + ",子线程结束执行...");
//            }
//        }).start();
//        for(int i = 0;i<10;i++){
//            new Thread(new Runnable() {
//                public void run() {
//                    try {
//                        Thread.sleep(500);
//                        System.out.println("开始执行:" + Thread.currentThread().getName());
//                        countDownLatch.countDown();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//    }

//    public static void main(String[] args) {
//        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
//        for (int i = 0; i < 5; i++) {
//            new Thread(new Runnable() {
//                public void run() {
//                    try {
//                        System.out.println("等待执行:" + Thread.currentThread().getName());
//                        cyclicBarrier.await();
//                        System.out.println("开始执行:" + Thread.currentThread().getName());
//                    } catch (InterruptedException | BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//    }

    public static void main(String[] args) {
        Semaphore semp = new Semaphore(5);
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("排队上厕所:" + Thread.currentThread().getName());
                        semp.acquire();//申请资源
                        System.out.println("当前厕所剩余数:" + semp.availablePermits());
                        Thread.sleep(500);
                        System.out.println("厕所上完了:" + Thread.currentThread().getName());
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semp.release();//释放资源
                    }
                }
            }).start();
        }
    }


    public void demo(){
        /**
         * 线程安全
         *
         * 多线程有三大特性
         *
         * Java内存模型
         *
         * synchronized
         *
         * lock
         *
         * 重排序
         *
         * Volatile
         *
         * Atomic
         *
         *锁分类：
         *
         *
         * 分布式锁
         */

        /**
         * 多线程之间通讯
         *加锁，lock
         * wait、notify  Condition
         * Volatile，Atomic
         *CountDownLatch
         * CyclicBarrier
         * Semaphore
         */

        /**
         * 线程池
         */

        /**
         * 并发队列
         *
         */



    }
}
