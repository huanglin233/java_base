package com.hl.javase.thread.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin
 * @date 2024/01/06 01:46
 */
public class ScheduledExecutor {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, Executors.defaultThreadFactory());
        executor.scheduleWithFixedDelay(new Runnable(){
            @Override
            public void run() {
                System.out.println("延迟1秒后执行，每次执行间隔10秒，time = " + System.currentTimeMillis());
            }
        }, 1, 10, TimeUnit.SECONDS);

    }
}
