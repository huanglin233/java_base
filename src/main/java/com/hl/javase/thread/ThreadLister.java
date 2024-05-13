package com.hl.javase.thread;

import java.util.Map;

/**
 * @author huanglin
 * @date 2024/05/13 20:38
 */
public class ThreadLister {

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for(Thread thread : allStackTraces.keySet()) {
            System.out.println("thread " + thread.getName() + " (ID = " + thread.getId() + ")");
        }
    }
}
