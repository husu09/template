package com.su.concurrency;

import org.junit.Test;

/**
 * @author husu
 * 使用字符串加锁
 * 对一变量 set方法加锁 get方法不加锁，验证不出来同步性的问题
 */
public class StringLockTest {
	private static class Person {
		private int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
		
	}
	
	private static Person p = new Person();
	
	public static void main(String[] args) throws InterruptedException {
		
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				synchronized ("STRING_LOCK".intern()) {
					for (int i = 0; i < 10000000; i++) {
						for (long l = 0; l < Integer.MAX_VALUE * 4L; l++) {
							
						}
						p.setAge(p.getAge() + 1);
						System.out.println(Thread.currentThread().getName() + " : " + p.getAge());
					}
				}
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				while (true) {
					for (long l = 0; l < Integer.MAX_VALUE * 4L; l++) {
						
					}
					System.out.println(Thread.currentThread().getName() + " : " + p.getAge());
				}
			}
		};
		Thread t1 = new Thread(r1, "t1");
		Thread t2 = new Thread(r1);
		Thread t3 = new Thread(r2, "t3");
		t1.start();
		//t2.start();
		t3.start();
		t1.join();
		t2.join();
		System.out.println(p.getAge());
	}
	
	
	@Test
	public void t1() {
		long start = System.currentTimeMillis();
		for (long l = 0; l < Integer.MAX_VALUE * 4L; l++) {
			
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
