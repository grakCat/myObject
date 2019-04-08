package org.gars.mars.safety;

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
