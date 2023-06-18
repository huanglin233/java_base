package com.hl.javase.thread.priorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue 测试类
 *
 * @author huanglin by 2021/5/15
 */
public class DoMain {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Person> priorityBlockingQueue = new PriorityBlockingQueue<Person>();
        priorityBlockingQueue.add(new Person(3, "personId3"));
        System.out.println("容器为：" + priorityBlockingQueue);
        priorityBlockingQueue.add(new Person(2, "personId2"));
        System.out.println("容器为：" + priorityBlockingQueue);
        priorityBlockingQueue.add(new Person(5, "personId5"));
        System.out.println("容器为：" + priorityBlockingQueue);
        priorityBlockingQueue.add(new Person(7, "personId7"));
        System.out.println("容器为：" + priorityBlockingQueue);
        priorityBlockingQueue.add(new Person(1, "personId1"));
        System.out.println("容器为：" + priorityBlockingQueue);
        priorityBlockingQueue.add(new Person(4, "personId4"));
        System.out.println("容器为：" + priorityBlockingQueue);
        System.out.println("=========================================>");

        System.out.println("获取元素: " + priorityBlockingQueue.take().toString());
        System.out.println("容器为：" + priorityBlockingQueue);
        System.out.println("=========================================>");

        System.out.println("获取元素: " + priorityBlockingQueue.take().toString());
        System.out.println("容器为：" + priorityBlockingQueue);
        System.out.println("=========================================>");

        System.out.println("获取元素: " + priorityBlockingQueue.take().toString());
        System.out.println("容器为：" + priorityBlockingQueue);
        System.out.println("=========================================>");

        System.out.println("获取元素: " + priorityBlockingQueue.take().toString());
        System.out.println("容器为：" + priorityBlockingQueue);
        System.out.println("=========================================>");
    }
}