package com.hl.javase.thread.threadLocal;

import java.util.Random;

/**
 * @author huanglin
 * @date 2024/02/14 19:37
 */
public class ThreadLocalTest implements Runnable{

    ThreadLocal<Student> studentThreadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        Random random = new Random();
        int    age    = random.nextInt(10);
        System.out.println(currentThreadName + " is set age: " + age);
        Student student = getStudent();
        student.setAge(age);
        System.out.println(currentThreadName + " is first get age: " + student.getAge());
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(currentThreadName + " is second get age: " + student.getAge());
    }

    private Student getStudent() {
        Student student = studentThreadLocal.get();
        if(null == student) {
            student = new Student();
            studentThreadLocal.set(student);
        }

        return student;
    }
}
