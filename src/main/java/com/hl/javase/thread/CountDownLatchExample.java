package com.hl.javase.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huanglin by 2021/5/15
 */
public class CountDownLatchExample {

    private static final int THREAD_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        ThreadPoolExecutor   executors      = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

        AtomicInteger atomicInteger = new AtomicInteger(0);
        int loop = 6;
        for(int i = 0; i < loop; i++) {
            executors.execute(() -> {
                int i1 = atomicInteger.incrementAndGet();
                try{
                    Thread.sleep(1000L * i1);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("xxxxxxxxxxxx" + i1);
            });
        }
        countDownLatch.await();
        executors.shutdown();
        System.out.println("finish");
    }
}
