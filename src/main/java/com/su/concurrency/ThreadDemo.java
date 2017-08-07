package com.su.concurrency;

import org.junit.Test;

public class ThreadDemo {
	
	/**
	 * 中断线程
	 */
	@Test
	public void interrupt() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					// 判断是否设置中断标识
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("优雅退出");
						break;
					}
					Thread.yield();
				}
			}
		}, "t1");
		
		t1.start();
		// 中断线程，并不会主动中断线程，而是设置一个中断标识
		t1.interrupt();
	}
	
	/**
	 * 中断异常
	 * 当线程 sleep 时被中断就会抛出 InterruptedException
	 * */
	@Test
	public void interruptedException() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					// 判断是否设置中断标识
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("优雅退出");
						break;
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						//e.printStackTrace();
						System.out.println("中断异常 " + Thread.currentThread().isInterrupted());
						// 抛出异常后，会清除中断标识，所以为了程序正常中断需要重新中断
						Thread.currentThread().interrupt();
					}
				}
			}
		}, "t1");
		
		t1.start();
		// 中断线程，并不会主动中断线程，而是设置一个中断标识
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.interrupt();
	}
	
	/**
	 * 被 join 的线程执行完成后，主线程才会继续执行
	 */
	@Test
	public void join() {
		class MyThread extends Thread {
			private int i = 0;
			
			public MyThread(String string) {
				super(string);
			}

			@Override
			public void run() {
				for (i = 0; i < 1000; i++);
			}
			
			public int get() {
				return i;
			}
			 
		}
		
		MyThread t1 = new MyThread("t1");
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t1.get());
	}
}
