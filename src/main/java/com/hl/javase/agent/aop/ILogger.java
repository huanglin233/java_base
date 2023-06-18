package com.hl.javase.agent.aop;

import java.lang.reflect.Method;

/**
 * @author huanglin
 * @date 2021/6/6 上午10:51
 */
public interface ILogger {
    /**
     * 方法日志记录开始
     * @param method 执行的方法
     */
    void start(Method method);

    /**
     * 方法日志记录结束
     * @param method 执行的方法
     */
    void end(Method method);
}