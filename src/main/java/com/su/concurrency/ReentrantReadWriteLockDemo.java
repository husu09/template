package com.su.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author husu
 * ReentrantReadWriteLock 读写锁，适用于读多写少的情况
 * 读读不互斥
 * 读写互斥
 * 写写互斥
 */
public class ReentrantReadWriteLockDemo implements Runnable {
	// 读写锁
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private static int i = 0;
	private int type = 0;
	
	public ReentrantReadWriteLockDemo(int type) {
		this.type = type;
	}
	
	@Override
	public void run() {
		if (type == 0) {
			// 读锁
			lock.readLock().lock();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
			lock.readLock().unlock();
		} else if (type == 1) {
			// 写锁
			lock.writeLock().lock();
			for (int i = 0; i < 1000000; i++) {
				this.i++;
			}
			lock.writeLock().unlock();
		}
		
	}
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(6);
		executor.submit(new ReentrantReadWriteLockDemo(1));
		executor.submit(new ReentrantReadWriteLockDemo(1));
		executor.submit(new ReentrantReadWriteLockDemo(1));
		executor.submit(new ReentrantReadWriteLockDemo(1));
		executor.submit(new ReentrantReadWriteLockDemo(1));
		executor.submit(new ReentrantReadWriteLockDemo(0));
	}
	
}
