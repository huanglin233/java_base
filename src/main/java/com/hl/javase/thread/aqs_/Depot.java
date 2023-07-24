package com.hl.javase.thread.aqs_;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huanglin
 * @date 2023/07/24 23:03
 */
public class Depot {

    private int       size;
    private int       capacity;
    private Lock      lock;
    private Condition fullCondition;
    private Condition emptyCondition;

    public Depot(int capacity) {
        this.capacity = capacity;
        lock           = new ReentrantLock();
        fullCondition  = lock.newCondition();
        emptyCondition = lock.newCondition();
    }

    public void produce(int no) {
        lock.lock();
        int left = no;
        try {
            while(left > 0) {
                while(size >= capacity) {
                    System.out.println(Thread.currentThread() + " before await");
                    fullCondition.await();
                    System.out.println(Thread.currentThread() + " after await");
                }

                int inc = (left + size) > capacity ? (capacity - size) : left;
                left -= inc;
                size += inc;
                System.out.println("produce = " + inc + ", size = " + size);
                emptyCondition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer(int no) {
        lock.lock();
        int left = no;
        try {
            while(left > 0) {
                while(size <= 0) {
                    System.out.println(Thread.currentThread() + "before await");
                    emptyCondition.await();
                    System.out.println(Thread.currentThread() + "after await");
                }

                int dec = (size - left) > 0 ? left : size;
                left -= dec;
                size -= dec;
                System.out.println("consumer = " + dec + ", size = " + size);
                fullCondition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
