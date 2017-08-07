package com.su.concurrency;

public class SynchronizedDemo implements Runnable {
	
	private static SynchronizedDemo instance = new SynchronizedDemo();
	
	private int i = 0;
	
	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 10000000; i++) {
				this.i ++;
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(instance.i);
	}
}
