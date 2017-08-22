package com.su.concurrency;

import java.util.concurrent.locks.LockSupport;

/**
 * @author husu
 * LockSupport 可以替代不安全的 t1.suspend() t1.resume() 操作
 * LockSupport.unpark(t1) 恢复操作在挂起之前也不会有问题，当 LockSupport.park() 的时候会判断前面是否有 unpark() 操作，
 * 有的话不会挂起
 */
public class LockSupportDemo implements Runnable {
	private static LockSupportDemo instance = new LockSupportDemo();

	@Override
	public void run() {
		// 挂机当前线程
		System.out.println(Thread.currentThread().getName() + " 挂起");
		// t1.interrupt() 也会恢复线程，并且不会抛出 InterruptException
		LockSupport.park();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 执行完毕");
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(instance, "t1");
		t1.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t1.getName() + " 恢复");
		LockSupport.unpark(t1);
	}
}
