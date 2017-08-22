package com.su.concurrency.question;

/**
 * @author husu
 * 无锁的 get set
 *
 */
public class UnlockGgetSet {
	
	public static void main(String[] args) throws InterruptedException {
		Person p = new Person();
		
		new SetThread(p).start();
		
		Thread.sleep(1000);
		
		new GetThread(p).start();
	}
}
