package com.hl.javase.thread.future_;

import com.hl.javase.test.P;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author huanglin
 * @date 2024/01/02 22:29
 */
public class FutureTask_ {

    public static void main(String[] args) {
        ExecutorService     service    = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
        service.submit(futureTask);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Thread [ " + Thread.currentThread().getName() + " ] is running");
        if(!futureTask.isDone()) {
            System.out.println("Task is not done");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int ret = 0;
        try {
            ret = futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ret is " + ret);
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Thread [ " + Thread.currentThread().getName() + " ] is running");
            int ret = 0;
            for(int i = 1; i <= 100; i++) {
                ret += i;
            }
            Thread.sleep(3000);

            return ret;
        }
    }
}
