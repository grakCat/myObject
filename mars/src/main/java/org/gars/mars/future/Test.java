package org.gars.mars.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created on 2018/11/9.
 *
 * @author grayCat
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        FutureInfo info = new FutureInfo();
        FutureTask<FutureInfo> task = new FutureTask<>(new FutureCallable(info));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(task);
        executor.shutdown();
        // 第二步 去超市购买食材
        // 第三步 用厨具烹饪食材
        FutureInfo chuju = task.get();
//        if (!task.isDone()) {  // 联系快递员，询问是否到货
//            System.out.println("第三步：厨具还没到，心情好就等着（心情不好就调用cancel方法取消订单）");
//        }

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
