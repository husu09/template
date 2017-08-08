package com.su.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * @author husu
 * 计数器
 */
public class CountDownLatchDemo {
	// 初始化计数器，当计数为0时，await 的线程继续执行
	private static CountDownLatch latch = new CountDownLatch(1);
	
	public static void main(String[] args) {
		try {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("latch.await()");
					latch.countDown();
				}
			}).start();
			// 程序在些阻塞
			latch.await();
			System.out.println(Thread.currentThread().getName() + " 继续执行");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
