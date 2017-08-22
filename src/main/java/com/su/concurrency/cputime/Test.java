package com.su.concurrency.cputime;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Executor executor = new Executor();
		MyThread t1 = new MyThread(executor, "t1");
		MyThread t2 = new MyThread(executor, "t2");
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}
}
