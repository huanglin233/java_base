package com.hl.javase.thread.threadPool;

import java.util.concurrent.*;

/**
 * @author huanglin
 * @date 2024/01/02 23:34
 */
public class MyWorkerPool {

    public static void main(String[] args) throws Exception{
        MyRejectedExecutionHandler myReject      = new MyRejectedExecutionHandler();
        ThreadFactory              threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor         threadPool    = new ThreadPoolExecutor(2, 4, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
                threadFactory, myReject);

        MyMonitorThread myMonitorThread = new MyMonitorThread(threadPool, 3);
        Thread          monitor         = new Thread(myMonitorThread);
        monitor.start();

        for(int i = 0; i < 10; i++) {
            threadPool.execute(new MyWorkerThread("cmd" + i));
        }

        Thread.sleep(30000);
        threadPool.shutdown();
        Thread.sleep(1000);
        myMonitorThread.shutdown();
    }
}
