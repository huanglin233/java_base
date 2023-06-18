package com.hl.javase.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author huanglin by 2021/5/17
 */
public class Test2 {
    private static final int  CORE_POOL_SIZE = 5;
    private static final int  MAX_POOL_SIZE = 10;
    private static final int  QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        List<Future<String>> futureList = new ArrayList<Future<String>>();
        Callable<String>     callable   = new MyCallable();
        int                  loop       = 10;
        for(int i = 0 ; i < loop; i++) {
            Future<String> submit = threadPoolExecutor.submit(callable);
            futureList.add(submit);
        }

        for(Future<String> future : futureList) {
            try {
                System.out.println(new Date() + "::" + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        threadPoolExecutor.shutdown();
    }
}