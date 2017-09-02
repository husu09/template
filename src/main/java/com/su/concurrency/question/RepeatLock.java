package com.su.concurrency.question;

public class RepeatLock {
	
	private static Object lock = new Object();
	
	/**
	 * 一个线程重复加锁是否有问题
	 */
	public static void main(String[] args) {
		synchronized (lock) {
			synchronized (lock) {
				System.out.println("重复加锁");
			}
		}
	}
}
