package com.hl.javase.agent.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author huanglin
 * @date 2021/6/6 上午10:56
 */
public class DynaProxyHello implements InvocationHandler {

    /**
     * 代理对象
     */
    private Object proxy;

    /**
     * 目标对象
     */
    private Object target;

    public Object bind(Object target, Object proxy) {
        this.target = target;
        this.proxy  = proxy;

        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        Class<?> aClass = this.proxy.getClass();
        Method start = aClass.getDeclaredMethod("start", new Class[]{Method.class});
        result = start.invoke(this.proxy, new Object[]{method});
        method.invoke(this.target, args);
        Method end = aClass.getDeclaredMethod("end", new Class[]{Method.class});
        end.invoke(this.proxy, new Object[]{method});

        return result;
    }
}
