package com.hl.javase.agent.aop;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author huanglin
 * @date 2021/6/6 上午10:53
 */
public class DLogger implements ILogger{

    @Override
    public void start(Method method) {
        System.out.println(new Date() + " => " + method.getName() + " start do ");
    }

    @Override
    public void end(Method method) {
        System.out.println(new Date() + " => " + method.getName() + " end do ");
    }
}