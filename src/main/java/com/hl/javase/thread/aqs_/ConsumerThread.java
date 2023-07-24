package com.hl.javase.thread.aqs_;

/**
 * @author huanglin
 * @date 2023/07/24 23:37
 */
public class ConsumerThread {

    private Depot depot;

    public ConsumerThread(Depot depot) {
        this.depot = depot;
    }

    public void consumer(int no) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                depot.consumer(no);
            }
        }, no + " consumer thread").start();
    }
}
