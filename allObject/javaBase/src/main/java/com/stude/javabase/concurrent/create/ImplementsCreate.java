package com.stude.javabase.concurrent.create;

/**
 * Created on 2019/4/7.
 *
 * @author Grak
 * @since 1.0
 */
public class ImplementsCreate implements Runnable {

    @Override
    public void run() {
        //设置当前线程是守护线程（调用当前线程的主线程停止，该线程也会停止）
        Thread thread = Thread.currentThread();
        thread.setDaemon(true);
    }
}
