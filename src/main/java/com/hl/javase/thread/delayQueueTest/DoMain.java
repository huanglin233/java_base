package com.hl.javase.thread.delayQueueTest;

import java.util.concurrent.*;

/**
 * @author huanglin by 2021/5/15
 */
public class DoMain {

	private static final DelayQueue<MyDelayedTask> DELAY_QUEUE = new DelayQueue<MyDelayedTask>();
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				DELAY_QUEUE.offer(new MyDelayedTask("task1", 10000));
				DELAY_QUEUE.offer(new MyDelayedTask("task2", 3900));
				DELAY_QUEUE.offer(new MyDelayedTask("task3", 5000));
				DELAY_QUEUE.offer(new MyDelayedTask("task4", 200));
				DELAY_QUEUE.offer(new MyDelayedTask("task5", 6900));
				DELAY_QUEUE.offer(new MyDelayedTask("task6", 7900));
				DELAY_QUEUE.offer(new MyDelayedTask("task7", 8900));
				DELAY_QUEUE.offer(new MyDelayedTask("task8", 5900));
				DELAY_QUEUE.offer(new MyDelayedTask("task9", 4900));
				DELAY_QUEUE.offer(new MyDelayedTask("task10", 2900));
			}
		}).start();


		while(true) {			
			Delayed take = DELAY_QUEUE.take();
			System.out.println(take);
		}
	}
}
