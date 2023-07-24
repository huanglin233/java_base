package com.hl.javase.thread.aqs_;

/**
 * @author huanglin
 * @date 2023/07/24 23:40
 */
public class ProducerThread {

    private Depot depot;

    public ProducerThread(Depot depot) {
        this.depot = depot;
    }

    public void producer(int no) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                depot.produce(no);
            }
        }, no + " produce thread").start();
    }
}
