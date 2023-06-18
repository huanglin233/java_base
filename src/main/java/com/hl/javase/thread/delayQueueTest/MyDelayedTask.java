package com.hl.javase.thread.delayQueueTest;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 
 * DelayQueue只能添加(offer/put/add)实现了Delayed接口的对象，意思是说我们不能想往DelayQueue里添加什么就添加什么，不能添加int、
 * 也不能添加String进去，必须添加我们自己的实现了Delayed接口的类的对象
 * 
 * @author huanglin by 2021/5/15
 *
 */
public class MyDelayedTask implements Delayed{
	
	private final String name;
	private final long   start = System.currentTimeMillis();
	private final long   time;
	
	public MyDelayedTask(String name, long time) {
		this.name = name;
		this.time = time;
	}
	
	/**
	 * 用于延迟队列内部比较排序 当前时间的延迟时间 - 比较对象延迟的时间
	 */
	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		MyDelayedTask myDelayedTask = (MyDelayedTask)o;
		return (int)(this.getDelay(TimeUnit.MICROSECONDS) - o.getDelay(TimeUnit.MICROSECONDS));
	}
	
	/**
	 * 需要实现接口，获得延迟时间 用过期时间-当前时间
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MyDelayedTask{" +
        		"name='" + name + '\'' +
        		", time=" + time +
        		'}';
	}
}