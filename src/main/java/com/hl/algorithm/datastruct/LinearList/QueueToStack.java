package com.hl.algorithm.datastruct.LinearList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈
 * @author huanglin
 * @date 2024/06/20 20:20
 */
public class QueueToStack {

    private Queue<Integer> queue;

    public QueueToStack() {
        queue = new LinkedList<>();
    }

    /**
     * 插入一个数到队首
     * @param val 值
     */
    public void push(int val) {
        queue.add(val);
        int cnt = queue.size();
        while(cnt-- > 1) {
            queue.add(queue.poll());
        }
    }

    /**
     * 取出队首数据,并删除
     * @return 队首数据
     */
    public int pop() {
        return queue.remove();
    }

    /**
     * 取出队首数据,不删除
     * @return 队首数据
     */
    public int top() {
        return queue.peek();
    }

    /**
     * 判断是否为空
     * @return 是否为空
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
