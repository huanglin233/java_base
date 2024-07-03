package com.hl.algorithm.datastruct.LinearList;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author huanglin
 * @date 2024/06/23 16:02
 */
public class DoMain {
    
    /**
     * 用栈实现括号匹配 "()[]{}"
     * @param str 配置字符串
     * @return
     */
    public boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for(char c : str.toCharArray()){
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if(c == ')' || c == ']' || c == '}') {
                Character pop = stack.pop();
                boolean b1 = c == ')' && pop == '(';
                boolean b2 = c == ']' && pop == '[';
                boolean b3 = c == '}' && pop == '{';
                if(!b1 && !b2 && !b3) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


    
    @Test
    public void isValidTest() {
        System.out.println(isValid("()[232]{111}"));
        System.out.println(isValid("(]"));
    }

    /**
     * 数组中元素于下一个比他大的元素之间的距离
     * @param temperatures 数组
     * @return int[]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int            num    = temperatures.length;
        int[]          dist   = new int[num];
        Stack<Integer> indexs = new Stack<Integer>();
        for(int curIndex = 0; curIndex < num; curIndex++) {
            while(!indexs.isEmpty() && temperatures[curIndex] <= temperatures[indexs.peek()]) {
                int preIndex = indexs.pop();
                dist[preIndex] = curIndex - preIndex;
            }

            indexs.add(curIndex);
        }

        return dist;
    }

    /**
     * 循环数组中比当前元素大的下一个元素
     * @param nums 循环数组
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int   num  = nums.length;
        int[] next = new int[num];
        Arrays.fill(next, -1);
        Stack<Integer> pre = new Stack<Integer>();
        for (int i = 0; i < num * 2; i++) {
            int n = nums[i % num];
            while(!pre.isEmpty() && nums[pre.peek()] < n) {
                next[pre.pop()] = n;
            }

            if(i < num) {
                pre.push(i);
            }
        }

        return next;
    }
}
