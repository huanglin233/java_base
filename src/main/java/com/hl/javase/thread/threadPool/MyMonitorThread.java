package com.hl.javase.thread.threadPool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author huanglin
 * @date 2024/01/02 23:22
 */
public class MyMonitorThread implements Runnable{

    /**
     * ThreadPoolExecutor 提供了一些方法，我们可以使用这些方法来查询 executor 的当前状态，线程池大小，活动线程数量以及任务数量
     */
    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        this.seconds  = delay;
    }

    public void shutdown() {
        this.run = false;
    }

    @Override
    public void run() {
        while(run) {
            System.out.print("线程池当前程数: " + executor.getPoolSize());
            System.out.print(",线程池核心线程数: " + executor.getCorePoolSize());
            System.out.print(",线程池中正在执行任务的线程数量: " + executor.getActiveCount());
            System.out.print(",线程池线程完成任务数: " + executor.getCompletedTaskCount());
            System.out.print(",线程池已经执行的和未执行的任务总数: " + executor.getTaskCount());
            System.out.print(",线程池是否关掉: " + executor.isShutdown());
            System.out.print(",线程池是否终止: " + executor.isTerminated());
            System.out.println();
            try {
                Thread.sleep(seconds * 1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
