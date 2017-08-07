package com.su.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

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
	
	/**
	 * 守护线程，守护线程在主线程执行完成后随之退出
	 */
	@Test
	public void daemon() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		// 设置该线程为守护线程，只能在线程开启前设置
		t1.setDaemon(true);
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 *  线程优先级，高优先级线程会获得更多执行机会
	 */
	@Test
	public void priority() {
		Runnable r1 = new Runnable() {
			private int i = 0;
			
			@Override
			public void run() {
				synchronized (this) {
					for (i = 0; i < 1000000000; i++);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(i + " " + Thread.currentThread().getName());
				}
			}
		};
		Thread t1 = new Thread(r1, "高优先级线程");
		t1.setPriority(Thread.MAX_PRIORITY);
		Thread t2 = new Thread(t1, "低优先级线程");
		t2.setPriority(Thread.MIN_PRIORITY);
		t2.start();
		t1.start();
		try {
			t2.join();
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * synchronizedz, 同步代码块保证共享数据的安全
	 */
	@Test
	public void synchronizedz() {
		class MyRunnable implements Runnable {
			AtomicInteger i = new AtomicInteger();
			
			@Override
			public void run() {
				synchronized (this) {
					for(; i.get() < 1000000; i.incrementAndGet()) {
					}
					System.out.println(i);
				}
			}
			
		}
		Runnable r2 = new MyRunnable();
		Thread t1 = new Thread(r2, "t1");
		Thread t2 = new Thread(r2, "t2");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
	// wait(), notifly()
}
