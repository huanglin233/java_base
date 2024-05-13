package com.hl.javase.thread.condition_;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界得缓冲队列
 * @author huanglin
 * @date 2024/05/13 22:54
 */
public class BoundedBuffer<T> {

    // 缓冲区列表
    private final LinkedList<T> buffer;
    // 缓冲区容量
    private final int capacity;
    // 互斥锁
    private final ReentrantLock lock;
    // 条件变量： 用于生产者通知消费者
    private final Condition notEmpty;
    // 条件变量： 用于消费者通知生产者
    private final Condition notFull;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer   = new LinkedList<>();
        this.lock     = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull  = lock.newCondition();
    }

    /**
     * 添加一个元素
     * @param t 添加得元素
     * @throws InterruptedException
     */
    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == capacity) {
                notFull.await();
            }
            buffer.add(t);
            notEmpty.signal();
        } catch (Exception e) {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                notEmpty.await();
            }
            T t = buffer.removeFirst();
            notFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }
}
