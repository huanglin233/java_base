package com.hl.javase.thread.exchanger_;

import com.hl.javase.test.P;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin
 * @date 2024/02/14 13:01
 */
public class Consumer extends Thread{

    private static int data = 0;
    private Exchanger<Integer> exchanger;

    public Consumer(String name, Exchanger<Integer> exchanger) {
        super("Consumer: " + name);
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            data = 0;
            System.out.println(getName() + " 交换前:" + data);
            try {
                TimeUnit.SECONDS.sleep(1);
                data = exchanger.exchange(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " 交换后:" + data);
        }
    }
}
