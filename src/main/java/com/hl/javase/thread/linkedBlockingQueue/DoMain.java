package com.hl.javase.thread.linkedBlockingQueue;

import java.util.concurrent.*;

/**
 * LinkedBlockingQueue Demo
 * 
 * @author huanglin by 2021/5/15
 */
public class DoMain {

    public static void main(String[] args) {
        BlockingQueue<Integer>   queue     = new LinkedBlockingQueue<Integer>(5);
        ThreadPoolExecutor       executors = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());
        Producer                 producer  = new Producer(queue);

        int loop = 3;
        for(int i=0; i < loop; i++) {
            executors.execute(producer);
        }
        executors.execute(new Consumer(queue));

        executors.shutdown();
    }
}