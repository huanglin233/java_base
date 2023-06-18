package com.hl.javase.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin by 2021/5/17
 */
public class Test4ThreadToJ {

    /**
     * 采用 Runnable 接口方式创建的多条线程可以共享实例属性
     */
    private int i;

    /**同步增加方法*/
    private synchronized void inc() {
        i++;
        System.out.println(Thread.currentThread().getName() + " -- inc-- " + i);
    }

    /**同步减算方法*/
    private synchronized void dec() {
        i--;
        System.out.println(Thread.currentThread().getName() + " --dec-- " + i);
    }


    /**增加线程*/
    class Inc implements Runnable {

        @Override
        public void run() {
            inc();
        }
    }

    /**减算线程*/
    class Dec implements Runnable {

        @Override
        public void run() {
            dec();
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

        Test4ThreadToJ test = new Test4ThreadToJ();
        Inc            inc  = test.new Inc();
        Dec            dec  = test.new Dec();
        int            loop = 2;
        for(int i = 0 ; i < loop ; i++) {
            executor.execute(inc);
            executor.execute(dec);
        }
    }
}