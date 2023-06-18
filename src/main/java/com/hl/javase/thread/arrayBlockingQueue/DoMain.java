package com.hl.javase.thread.arrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用ArrayBlockingQueue实现生产者和消费者(公平，非公平)
 * 先进先出对元素进行排序，默认情况下不保证访问者的公平。
 * 如需要：创建一个公平的阻塞队列 ArrayBlockingQueue queue = new ArrayBlockingQueue(1000, true);
 * 
 * @author huanglin by 2021/5/15
 *
 */
public class DoMain {

    private static final int                        CAPACITY = 10;
    private static final ArrayBlockingQueue<Breiad> QUEUE    = new ArrayBlockingQueue<Breiad>(CAPACITY);

    public static void main(String[] args) {
        ThreadPoolExecutor executors = new ThreadPoolExecutor(10, 20, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());
        int loop = 5;
        for(int i = 0; i < loop; i++) {
            executors.execute(new Producer(QUEUE));
            executors.execute(new Consumer(QUEUE));
        }

        executors.shutdown();
    }
}