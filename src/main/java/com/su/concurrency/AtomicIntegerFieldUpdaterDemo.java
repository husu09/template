package com.su.concurrency;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 利用 AtomicIntegerFieldUpdater 将不安全的变量，包装成线程安全的
 * 使用包装类，必须使用 volatile 修饰变量
 */
public class AtomicIntegerFieldUpdaterDemo {
	public volatile int v = 0;
	private static Object lock = new Object();
	private static AtomicIntegerFieldUpdaterDemo instace = new AtomicIntegerFieldUpdaterDemo();

	public static void main(String[] args) throws InterruptedException {
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				// 加锁的方式保证累加操作的原子性
				/*
				 * synchronized (lock) { for (int i = 0; i < 1000000; i++) {
				 * instace.v += 2; } }
				 */

				// 使用原子包装类实现原子性
				AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterDemo> ato = AtomicIntegerFieldUpdater
						.newUpdater(AtomicIntegerFieldUpdaterDemo.class, "v");

				for (int i = 0; i < 1000000; i++) {
					ato.addAndGet(instace, 2);
				}
			}
		};

		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(instace.v);
		// 4000000
	}
}
