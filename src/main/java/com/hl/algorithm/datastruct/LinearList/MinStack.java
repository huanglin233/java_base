package com.hl.algorithm.datastruct.LinearList;

import java.util.Stack;

/**
 * 最小栈
 * @author huanglin
 * @date 2024/06/20 22:55
 */
public class MinStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    private int min;

    public MinStack() {
        dataStack = new Stack<>();
        minStack  = new Stack<>();
        min       = Integer.MAX_VALUE;
    }

    /**
     * 向栈插入数据
     * @param x 值
     */
    public void push(int x) {
        dataStack.push(x);
        min = Math.min(min, x);
        minStack.push(min);
    }

    /**
     * 获取栈首数据,并删除
     * @return 栈首数据
     */
    public int pop() {
        int ret = dataStack.pop();
        minStack.pop();
        min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();

        return ret;
    }

    /**
     * 查询栈首数据,不删除
     * @return 栈首数据
     */
    public int top() {
        return dataStack.peek();
    }

    /**
     * 获取栈中最小值
     * @return 最小值
     */
    public int getMin() {
        return minStack.peek();
    }
}
