package com.hl.algorithm.datastruct.LinearList;

import java.util.Stack;

/**
 * 用两个栈实现一个队列
 * @author huanglin
 * @date 2024/06/19 21:01
 */
public class StackToQueue {

    private Stack<Integer> in  = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    /**
     * 插入数据
     * @param x 值
     */
    public void push(int x) {
        in.push(x);
    }

    /**
     * 取出数据并删除
     * @return 队首数据
     */
    public int pop() {
        inToOut();
        return out.pop();
    }

    /**
     * 取出数据不删除
     * @return 队首数据
     */
    public int peek() {
        inToOut();
        return out.peek();
    }

    /**
     * 入栈数据插入到出栈数据中
     */
    private void inToOut() {
        if(out.isEmpty()) {
            while(!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }

    /**
     * 判断是否为null
     * @return 是否为null
     */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
