package com.hl.javase.thread;

import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huanglin by 2021/5/15
 */
public class Test {

    private static final int  CORE_POOL_SIZE  = 5;
    private static final int  MAX_POOL_SIZE   = 10;
    private static final int  QUEUE_CAPACITY  = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
//        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(10, 10,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());
//        ThreadPoolExecutor executor2 = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
//        ThreadPoolExecutor executor3 = new ThreadPoolExecutor(1, 1,
//                        0L, TimeUnit.MILLISECONDS,
//                        new LinkedBlockingQueue<Runnable>());
//        ThreadPoolExecutor executor4 = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        ThreadPoolExecutor executor5 = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());


        /**
         * corePoolSize: 核心线程数为 5。
         * maximumPoolSize ：最大线程数 10
         * keepAliveTime : 等待时间为 1L。
         * unit: 等待时间的单位为 TimeUnit.SECONDS。
         * workQueue：任务队列为 ArrayBlockingQueue，并且容量为 100;
         * handler:饱和策略为 CallerRunsPolicy。
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                /**
                 * ArrayBlockingQueue 阻塞有界限的队列
                 *  1.先进先出队列（队列头的是最先进队的元素；队列尾的是最后进队的元素）
                 *  2.有界队列（即初始化时指定的容量，就是队列最大的容量，不会出现扩容，容量满，则阻塞进队操作；容量空，则阻塞出队操作）
                 *  3.队列不支持空元素
                 * DelayQueue
                 * 	1.DelayQueue是一个无界的BlockingQueue，用于放置实现了Delayed接口的对象，其中的对象只能在其到期时才能从队列中取走。这种队列是有序的，即队头对象的延迟到期时间最长。注意：不能将null元素放置到这种队列中。
                 */
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                /**
                 * ThreadPoolExecutor.AbortPolicy**：抛出 RejectedExecutionException来拒绝新任务的处理。
                 * ThreadPoolExecutor.CallerRunsPolicy**：调用执行自己的线程运行任务，也就是直接在调用execute方法的线程中运行(run)被拒绝的任务，如果执行程序已关闭，则会丢弃该任务。因此这种策略会降低对于新任务提交速度，影响程序的整体性能。如果您的应用程序可以承受此延迟并且你要求任何一个任务请求都要被执行的话，你可以选择这个策略。
                 * ThreadPoolExecutor.DiscardPolicy： 不处理新任务，直接丢弃掉。
                 * ThreadPoolExecutor.DiscardOldestPolicy： 此策略将丢弃最早的未处理的任务请求。
                 */
                new ThreadPoolExecutor.CallerRunsPolicy());

        int loop = 10;
        for(int i = 0; i < loop; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable woke = new MyRunnable("" + i);
            threadPoolExecutor.execute(woke);
            threadPoolExecutor.shutdown();
        }

        //终止线程池
        threadPoolExecutor.shutdown();

        while (!threadPoolExecutor.isTerminated()) {
            System.out.println("is not finished");
        }

        System.out.println("Finished all threads");
    }
}