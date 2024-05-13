package com.hl.javase.thread.future_;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author huanglin
 * @date 2024/05/13 20:29
 */
public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步任务
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 10;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 20;
        });
        CompletableFuture<Integer> resultFuture = future1.thenCombine(future2, Integer::sum);

        System.out.println("sum: " + resultFuture.get());
    }
}
