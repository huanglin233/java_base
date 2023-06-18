package com.hl.javase;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author huanglin by 2021/5/17
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Singleton uniqueInstance = Singleton.getUniqueInstance();

        // 获取TargetObject类的Class对象并且创建TargetObject类实例
        Class<?>     aClass = Class.forName("com.hl.javase.TargetObject");
        TargetObject o      = (TargetObject) aClass.newInstance();

        TargetObject target = new TargetObject();
        // 通过Object类的getClass方法
        Class<? extends TargetObject> aClass1 = target.getClass();
        // 通过对象实例方法获取对象
        Class<TargetObject> aClass2 = TargetObject.class;

        // 获取所有类中所有定义的方法
        Method[] methods = aClass.getMethods();
        for(Method method : methods) {
            System.out.println(method.getName());
        }

        // 获取指定方法并调用
        Method publicMethod = aClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(o, "hello2");

        // 获取指定参数并对参数进行修改
        Field value = aClass.getDeclaredField("value");
        //为了对类中的参数进行修改我们取消安全检查
        value.setAccessible(true);
        value.set(o, "hello2");

        //调用 private 方法
        Method privateMethod = aClass.getDeclaredMethod("privateMethod", String.class);
        privateMethod.setAccessible(true);
        privateMethod.invoke(o, "hello2");
    }
}