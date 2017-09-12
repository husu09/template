package com.su.concurrency.question;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 多线程操作List
 */
public class ListTest {
	static List<Integer> list = new ArrayList<>();
	
	/**
	 * 一个线程写入，一个线程读取报错
	Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 13
	at java.util.ArrayList.add(ArrayList.java:459)
	at com.su.concurrency.question.ListTest$1.run(ListTest.java:20)
	at java.lang.Thread.run(Thread.java:745)
	 * */
	@Test
	public void t1() throws InterruptedException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					list.add(i);
				}
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					list.get(i);
				}
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
	}
	
	/**
	 * 一个线程写入，一个线程contains
	 * 导致死锁
	 * */
	@Test
	public void t2() throws InterruptedException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					list.add(i);
				}
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					list.contains(i);
				}
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
	}
	
	/**
	 * 一个线程写入，一个线程clear
	 * */
	@Test
	public void t3() throws InterruptedException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					list.add(i);
				}
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					list.clear();
				}
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(list);
	}
	
	
}
