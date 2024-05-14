package com.hl.javase.thread.threadLocal;

/**
 * 父子线程通过threadLocal共享变量
 * @author huanglin
 * @date 2024/05/14 20:41
 */
public class InheritableThreadLocalTest {

    public static void main(String[] args) {
        final ThreadLocal threadLocal = new InheritableThreadLocal();
        threadLocal.set("hello");

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("threadLocal: " + threadLocal.get());
            }
        };
        thread.start();
    }
}
