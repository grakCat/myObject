package org.gars.mars.pool;

import org.gars.mars.quartz.MyQuartz;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/11/30.
 * <p>
 * 当前线程池，会自定义线程配置。
 * 并在没有任务执行后关闭线程池，新任务进来会自动实例一个新的线程池。
 *
 * @author grayCat
 * @since 1.0
 */
@Component
public class CommonThreadPool implements Job {

    private Logger log = LoggerFactory.getLogger(getClass());
    /*定时任务执行类型*/
    private Constant.ExecutorType executorType;
    /*通用线程池*/
    private static ThreadPoolExecutor executorService;
    /*需求不算特别大的线程池*/
    private static ThreadPoolExecutor sampleService;
    /*定时控制*/
    private static MyQuartz quartz = new MyQuartz();

    public Constant.ExecutorType getExecutorType() {
        return executorType;
    }

    public void setExecutorType(Constant.ExecutorType executorType) {
        this.executorType = executorType;
    }

    private void sampleExecutor() {
        if (sampleService == null) {
            sampleService = new ThreadPoolExecutor(
                    1,
                    Runtime.getRuntime().availableProcessors() + 1,
                    10L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(20));

            //添加定时任务
            try {
                JobDataMap jobMap = new JobDataMap();
                jobMap.put("executorType", Constant.ExecutorType.SAMPLE);
                quartz.intervalTimmer(30, TimeUnit.SECONDS, CommonThreadPool.class, jobMap);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }

    private void instanceExecutor() {
        if (executorService == null) {
            int poolSize = Runtime.getRuntime().availableProcessors() + 1;
            executorService = new ThreadPoolExecutor(
                    poolSize,
                    poolSize,
                    0L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(800));
            //添加定时任务
            try {
                JobDataMap jobMap = new JobDataMap();
                jobMap.put("executorType", Constant.ExecutorType.COMMON);
                quartz.intervalTimmer(30, TimeUnit.SECONDS, CommonThreadPool.class, jobMap);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提交一个通用线程
     *
     * @param runnable
     */
    public void addThread(Runnable runnable) {
        instanceExecutor();
        executorService.submit(runnable);
    }

    /**
     * 提交一个简单线程
     */
    public void addSamplePool(Runnable runnable) {
        sampleExecutor();
        sampleService.submit(runnable);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ThreadPoolExecutor executor = executorType == Constant.ExecutorType.SAMPLE ? sampleService
                : executorType == Constant.ExecutorType.COMMON ? executorService
                : null;
        if (executor != null) {
            int queueSize = executor.getQueue().size();
            int activeCount = executor.getActiveCount();
            if (queueSize + activeCount <= 0) {
                executor.shutdown();
                if (executorType == Constant.ExecutorType.SAMPLE) {
                    sampleService = null;
                } else if (executorType == Constant.ExecutorType.COMMON) {
                    executorService = null;
                }
                log.debug("线程池空闲自动关闭：" + executorType);
                //删除当前已执行任务的循环定时器
                JobKey getKey = context.getJobDetail().getKey();
                try {
                    context.getScheduler().deleteJob(getKey);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
