package com.hl.javase.thread.exchanger_;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin
 * @date 2024/02/14 12:55
 */
public class Producer extends Thread{

    private static int data = 0;
    private Exchanger<Integer> exchanger;

    public Producer(String name, Exchanger<Integer> exchanger) {
        super("Producer" + name);
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for(int i = 1; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                data = i;
                System.out.println(getName() + " 交换前:" + data);
                data = exchanger.exchange(data);
                System.out.println(getName() + "交换后:" + data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
