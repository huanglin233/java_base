package com.hl.javase.thread.threadPool.myThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huanglin
 * @date 2024/05/16 22:56
 */
public class MyThreadPoolExecutor implements Executor {

    // 记录当前线程池中的线程数量
    private final AtomicInteger ctl = new AtomicInteger(0);
    // 核心线程数
    private volatile int corePoolSize;
    // 最大线程数
    private volatile int maximumPoolSize;
    /**
     * 待执行任务队列
     */
    private final BlockingQueue<Runnable> workQueue;

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize    = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue       = workQueue;
    }

    /**
     * 执行线程
     * @param command the runnable task
     */
    @Override
    public void execute(Runnable command) {
        // 当前工作线程
        int curThreadCount = ctl.get();
        // 小于核心线程数
        if(curThreadCount < corePoolSize) {
            // 添加任务
            if(!addWork(command)) {
                // 添加失败
                reject();
            }

            return;
        }

        // 任务队列添加任务
        if(!workQueue.offer(command)){
            // 任务队列满, 尝试启动线程添加任务
            if(!addWork(command)) {
                reject();
            }
        }
    }

    /**
     * 饱和拒绝
     */
    private void reject() {
        // 直接抛出异常
        throw new RuntimeException("Can not execute!ctl.count: " + ctl.get() + "workQueue size: " + workQueue.size());
    }

    /**
     * 添加任务
     * @param firstTask 添加的任务
     * @return
     */
    private boolean addWork(Runnable firstTask) {
        if(ctl.get() >= maximumPoolSize) {
            return false;
        }
        Worker worker = new Worker(firstTask);
        // 启动线程
        worker.thread.start();
        ctl.incrementAndGet();

        return true;
    }

    /**
     * 执行任务的线程
     */
    private final class Worker implements Runnable {

        final Thread thread;
        Runnable firstTask;

        public Worker(Runnable firstTask) {
            this.thread    = new Thread(this);
            this.firstTask = firstTask;
        }

        @Override
        public void run() {
            Runnable task = firstTask;
            try {
                // 判断队列是否还存再待执行的任务
                while(task != null || (task = getTask()) != null) {
                    // 执行任务
                    task.run();
                    if(ctl.get() > maximumPoolSize) {
                        // 线程池已满,无法再创建线程
                        break;
                    }
                    task = null;
                }
            } finally {
                // 新增任务线程
                ctl.decrementAndGet();
            }
        }
    }

    /**
     * 获取待执行的任务
     * @return
     */
    private Runnable getTask() {
        for(;;) {
            try {
                System.out.println("workQueue size:" + workQueue.size());
                return workQueue.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
