package com.su.concurrency;

import java.util.concurrent.Semaphore;

/**
 * @author husu
 * SemaphoreDemo 信号量
 * 限制多线程的并发
 */
public class SemaphoreDemo implements Runnable {
	private static SemaphoreDemo instance = new SemaphoreDemo();
	// 创建有 5 个许可(permits)的共享锁
	private static Semaphore semaphore = new Semaphore(5);
	@Override
	public void run() {
		try {
			// 获取许可
			semaphore.acquire();
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " 执行");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			// 释放许可
			semaphore.release();
		}
	}
	
	public static void main(String[] args) {
		Thread[] ts = new Thread[20];
		for (int i = 0; i < ts.length; i++) {
			Thread t = new Thread(instance, "t" + i);
			ts[i] = t;
		}
		for (int i = 0; i < ts.length; i++) {
			ts[i].start();
		}
	}

}
