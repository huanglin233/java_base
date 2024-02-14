package com.hl.javase.thread.future_;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin
 * @date 2024/01/01 22:02
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                long start = System.currentTimeMillis();
                while(true) {
                    long current = System.currentTimeMillis();
                    if((current - start) > 3000) {
                        return 1;
                    }
                }
            }
        });

        try {
           Integer ret = (Integer) future.get(4000, TimeUnit.MILLISECONDS);
           System.out.println(ret);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
