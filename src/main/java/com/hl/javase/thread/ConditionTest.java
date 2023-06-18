package com.hl.javase.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author huanglin
 * @date 2021/7/2 下午10:28
 */
public class ConditionTest {

    /**
     * 容器
     */
    private LinkedList<String> buffer;
    /**
     * 容器最大
     */
    private int maxSize;
    private Lock lock;
    private Condition fullCondition;
    private Condition notFullCondition;

    public ConditionTest(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        fullCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    public void set(String string){
            lock.lock();
        try {
            while (maxSize == buffer.size()) {
                notFullCondition.await();
            }
            buffer.add(string);
            fullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String string = null;
        lock.lock();
        try {
            while(buffer.size() == 0) {
                fullCondition.await();
            }
            string = buffer.poll();
            notFullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        
        return string;
    }
}