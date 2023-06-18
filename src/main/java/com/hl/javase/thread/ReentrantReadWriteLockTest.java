package com.hl.javase.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @author huanglin by 2021/5/16
 */
public class ReentrantReadWriteLockTest {

	/**
	 * 读写锁对象
	 */

	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

	/**
	 * 获取读锁
	 */

	private ReadLock readLock = rwLock.readLock();
	
	/**
	 * 获取写锁
	 */
	private WriteLock writeLock = rwLock.writeLock();
	
	public void read() {
		readLock.lock();
		try {
			System.out.println("线程" + Thread.currentThread().getName() + "进入了读方法");
			Thread.sleep(3000);
			System.out.println("线程" + Thread.currentThread().getName() + "推出了读方法");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	}
	
	public void write() {
		writeLock.lock();
		try {
			System.out.println("线程" + Thread.currentThread().getName() + "进入了写方法");
			Thread.sleep(3000);
			System.out.println("线程" + Thread.currentThread().getName() + "退出了写方法");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ReentrantReadWriteLockTest reentrantLock = new ReentrantReadWriteLockTest();
		ThreadPoolExecutor executors = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

		executors.execute(new ReadThread(reentrantLock));
		executors.execute(new WriteThread(reentrantLock));
		executors.execute(new WriteThread(reentrantLock));
		executors.execute(new ReadThread(reentrantLock));

	}
}

class ReadThread extends Thread {

	ReentrantReadWriteLockTest reentrantReadLock;

	public ReadThread(ReentrantReadWriteLockTest reentrantReadLock) {
		this.reentrantReadLock = reentrantReadLock;
	}

	@Override
	public void run() {
		reentrantReadLock.read();
	}
}

class WriteThread extends Thread {

	ReentrantReadWriteLockTest reentrantWriteLock;

	public WriteThread(ReentrantReadWriteLockTest reentrantWriteLock) {
		this.reentrantWriteLock = reentrantWriteLock;
	}

	@Override
	public void run() {
		reentrantWriteLock.write();
	}
}