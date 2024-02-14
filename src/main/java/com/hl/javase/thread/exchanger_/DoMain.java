package com.hl.javase.thread.exchanger_;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin
 * @date 2024/02/14 13:05
 */
public class DoMain {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(new Producer("t1", exchanger)).start();
        new Thread(new Consumer("t2", exchanger)).start();
        TimeUnit.SECONDS.sleep(7);
        System.exit(-1);
    }
}
