package com.hl.javase.reflect;

import com.hl.javase.reflect.test.Dog;

import java.lang.reflect.Method;

/**
 * @author huanglin
 * @date 2023/05/15 21:52
 */
public class ReflectMethod {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.hl.javase.reflect.test.Dog");

        // 根据参数获取public的Method,包含继承自父类的方法
        Method method = clazz.getMethod("eat", int.class, String.class);
        System.out.println("method: " + method.getName());

        // 获取所有public的方法
        Method[] methods = clazz.getMethods();
        for(Method m : methods) {
            System.out.println("m::" + m);
        }

        // 获取当前类的方法包含private,改方法无法继承自父类的method
        Method method_1 = clazz.getDeclaredMethod("eatTo");
        System.out.println("method_1::" + method_1);
        // 获取当前类的所有包含private,改方法无法获取继承自父类的method
        Method [] methods_1 = clazz.getDeclaredMethods();
        for(Method m : methods_1) {
            System.out.println("m1::" + m);
        }

        //创建对象
        Dog dog = (Dog) clazz.newInstance();
        //通过Method对象的invoke(Object obj,Object... args)方法调用
        method.invoke(dog, 1, "eag");
        // 对私有无参方法的操作
        method_1.setAccessible(true);
        method_1.invoke(dog);
        // 对有返回值的操作
        Method method_2 = clazz.getMethod("getCount");
        Integer count = (Integer) method_2.invoke(dog);
        System.out.println(count);
    }
}
