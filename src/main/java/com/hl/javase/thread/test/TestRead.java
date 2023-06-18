package com.hl.javase.thread.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin by 2021/5/15
 */
public class TestRead {
    public static void main(String[] args) throws InterruptedException {
        Integer [] array = {2, 4, 5, 6, 23, 8, 9};
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(array));

        ReadList readList = new ReadList(list);
        ThreadPoolExecutor executors = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());
        executors.execute(readList);

        Thread.sleep(1000 * 5);
        int loop = 50;
        for(int i = 30; i < loop; i++) {
            readList.list.add(i);
        }
        System.out.println("-----" + readList.list.size() + "-----");
        readList.notifyRead();
    }
}