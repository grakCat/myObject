package org.gars.mars.future;

import java.util.concurrent.*;

/**
 * Created on 2018/11/9.
 *
 * @author grayCat
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        try {
            //异步执行任务
            Callable callable = new FutureCallable(new FutureInfo());
            FutureTask<FutureInfo> task = new FutureTask<>(callable);
            //任务提交给线程池
            executor.submit(task);
            //任务是否已经执行完毕
            boolean isDone = task.isDone();
            //任务是否取消
            boolean isCanceller = task.isCancelled();
            //取消任务(true 强制停止，false已经开始的任务不会停止)
//            task.cancel(true);
            //获取结果的时候会柱塞等待结果
            FutureInfo chuju = task.get();
        }finally {
            executor.shutdown();
        }
    }
}
