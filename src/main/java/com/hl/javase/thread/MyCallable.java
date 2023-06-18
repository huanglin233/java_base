package com.hl.javase.thread;

import java.util.concurrent.Callable;

/**
 * @author huanglin by 2021/5/16
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);

        return Thread.currentThread().getName();
    }
}