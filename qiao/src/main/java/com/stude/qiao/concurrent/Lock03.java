package com.stude.qiao.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2019/4/2.
 *
 * @author Grak
 * @since 1.0
 */
public class Lock03 {

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Lock03 lock03 = new Lock03();
        lock03.runSyn();
        lock03.lockRun();
    }

    public void lockRun(){
        new Thread(){
            public void run(){
                lock.lock();
                System.out.println("进入方法1");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行方法1");
                lock.unlock();
            }
        }.start();
        new Thread(){
            public void run(){
                lock.lock();
                System.out.println("进入方法2");
                condition.signal();
                System.out.println("执行方法2");
                lock.unlock();
            }
        }.start();
    }

    public void runSyn(){
        new Thread(){
            public void run(){
                Lock03.this.run();
            }
        }.start();
        new Thread(){
            public void run(){
                run02();
            }
        }.start();
    }

    public void run(){
        synchronized (this){
            try {
                System.out.println("wait开始");
                this.wait();
                System.out.println("wait结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run02(){
        synchronized (this){
            System.out.println("nnotify开始");
            this.notify();
            System.out.println("nnotify结束");
        }
    }
}
