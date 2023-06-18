package com.hl.javase.agent.aop;

/**
 * @author huanglin
 * @date 2021/6/6 上午10:49
 */
public class Hello implements IHello{
    @Override
    public void sayHello(String str) {
        System.out.println("hello : " + str);
    }
}