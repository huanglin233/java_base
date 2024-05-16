package com.hl.javase.thread.threadPool.myThreadPool;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author huanglin
 * @date 2024/05/16 23:23
 */
public class DoMain {

    public static void main(String[] args) {
        MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor(2, 2, new ArrayBlockingQueue<Runnable>(10));
        for(int i = 0; i < 10; i++) {
            int taskNum = i;
            myThreadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("任务编号: " + taskNum);
            });
        }
    }

}
