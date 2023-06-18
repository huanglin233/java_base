package com.hl.javase.thread;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  @author huanglin by 2021/5/17
 */
public class ThreadLocalExample implements Runnable{
    private static final ThreadLocal<SimpleDateFormat> FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue()
        {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor           = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadLocalExample threadLocalExample = new ThreadLocalExample();

        int loop = 10;
        for(int i = 0; i < loop; i++) {
            Thread.sleep(new Random().nextInt(10000));
            executor.execute(threadLocalExample);
        }

        executor.shutdown();
    }

    @Override
    public void run() {
        System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter = " + FORMAT.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        FORMAT.set(new SimpleDateFormat());

        System.out.println("Thread Name = " + Thread.currentThread().getName() + " formatter = " + FORMAT.get().toPattern());
    }
}