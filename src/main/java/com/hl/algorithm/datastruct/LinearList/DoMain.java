package com.hl.algorithm.datastruct.LinearList;

import org.junit.Test;

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
}
