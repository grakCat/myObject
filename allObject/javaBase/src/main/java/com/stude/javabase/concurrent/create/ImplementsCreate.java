package com.stude.javabase.concurrent;

/**
 * Created on 2019/4/7.
 *
 * @author Grak
 * @since 1.0
 */
public class ImplementsCreate implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable");
    }
}
