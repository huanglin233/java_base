package com.hl.javase.thread.aqs_;

/**
 * @author huanglin
 * @date 2023/07/24 23:43
 */
public class ProducerAndConsumerTest {

    public static void main(String[] args) {
        Depot depot = new Depot(500);
        new ProducerThread(depot).producer(500);
        new ProducerThread(depot).producer(200);
        new ConsumerThread(depot).consumer(500);
        new ConsumerThread(depot).consumer(200);
    }
}
