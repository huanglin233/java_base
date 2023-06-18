package com.hl.javase.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 
 * @author huanglin
 * @date 2021/7/3 16:17
 */
public class ExchangerTest {

    static class Producer implements Runnable {

        // 生产者、消费者交换的数据结构
        private List<String> buffer;

        // 生产者和消费者的交换对象
        private Exchanger<List<String>> exchanger;

        public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer    = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for(int i = 1; i < 5; i++) {
                System.out.println("生产者第" + i + "次提供");
                for(int j = 1; j <= 3; j++) {
                    System.out.println("生产者装入" + i + "--" + j);
                    buffer.add("buffer: " + i + "--" + j);
                }
                System.out.println("生产者装满,等待与消息者交换...");
                try {
                    exchanger.exchange(buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        // 生产者、消费者交换的数据结构
        private List<String> buffer;

       // 生产者和消费者的交换对象
        private Exchanger<List<String>> exchanger;

        public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer    = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for(int i = 1; i < 5; i++) {
                // 调用exchange()与消费者进行数据交换
                try {
                    buffer = exchanger.exchange(buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("消费者第" + i + "次提取");
                for(int j = 1; j <= 3; j++) {
                    System.out.println("消费者 : " + buffer.get(0));
                    buffer.remove(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String>            buffer1   = new ArrayList<String>();
        List<String>            buffer2   = new ArrayList<String>();
        Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

        Thread producerThread = new Thread(new Producer(buffer1, exchanger));
        Thread consumerThread = new Thread(new Consumer(buffer2, exchanger));

        producerThread.start();
        consumerThread.start();
    }
}
