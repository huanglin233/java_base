package com.hl.javase.thread.synchronousqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue 测试类
 * 注意1：它一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。
 *        同步队列没有任何内部容量，甚至连一个队列的容量都没有。
 *  注意2：它是线程安全的，是阻塞的。
 *  注意3:不允许使用 null 元素。
 *  注意4：公平排序策略是指调用put的线程之间，或take的线程之间。
 *  公平排序策略可以查考ArrayBlockingQueue中的公平策略。
 *  注意5:SynchronousQueue的以下方法很有趣：
 *     * iterator() 永远返回空，因为里面没东西。
 *     * peek() 永远返回null。
 *     * put() 往queue放进去一个element以后就一直wait直到有其他thread进来把这个element取走。
 *     * offer() 往queue里放一个element后立即返回，如果碰巧这个element被另一个thread取走了，offer方法返回true，认为offer成功；否则返回false。
 *     * offer(2000, TimeUnit.SECONDS) 往queue里放一个element但是等待指定的时间后才返回，返回的逻辑和offer()方法一样。
 *     * take() 取出并且remove掉queue里的element（认为是在queue里的。。。），取不到东西他会一直等。
 *     * poll() 取出并且remove掉queue里的element（认为是在queue里的。。。），只有到碰巧另外一个线程正在往queue里offer数据或者put数据的时候，该方法才会取到东西。否则立即返回null。
 *     * poll(2000, TimeUnit.SECONDS) 等待指定的时间然后取出并且remove掉queue里的element,其实就是再等其他的thread来往里塞。
 *     * isEmpty()永远是true。
 *     * remainingCapacity() 永远是0。
 *     * remove()和removeAll() 永远是false。
 *
 * @author huanglin by 2021/5/15
 */
public class DoMain {
    public static void main(String[] args) {
        SynchronousQueue<String> syncQueue = new SynchronousQueue<String>();
        ThreadPoolExecutor       executors = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

        int loop = 5;
        for(int i = 0; i < loop; i++) {
            executors.execute(new Producer(syncQueue));
        }

        for(int i = 0; i < loop; i++) {
            executors.execute(new Consumer(syncQueue));
        }
    }
}