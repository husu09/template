package com.su.concurrency.question;

import org.junit.Test;

/**
 * 多线和赋值操作测试
 */
public class AssignmentTest {
	private static int i = 0;
	private static boolean flag;

	private static class Person {
		public String name;
		public int age;
		public boolean flag;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

	}

	private static volatile Person person = new Person("小明", 10);
	private static Object lock = new Object();

	/**
	 * 多线程累加操作
	 */
	@Test
	public void t1() throws InterruptedException {
		Runnable t1 = new Runnable() {

			@Override
			public void run() {
				synchronized (lock) {
					for (int j = 0; j < 1000000; j++) {
						i++;
					}
				}

			}
		};
		Runnable t2 = new Runnable() {

			@Override
			public void run() {
				synchronized (lock) {
					for (int j = 0; j < 1000000; j++) {
						i++;
					}
				}

			}
		};

		new Thread(t1).start();
		new Thread(t2).start();
		Thread.sleep(3000);
		System.out.println(i);
	}

	/**
	 * 多线程对对象中的属性进行累加操作，不加锁异常
	 */
	@Test
	public void t2() throws InterruptedException {
		Runnable t1 = new Runnable() {

			@Override
			public void run() {
				for (int j = 0; j < 1000000; j++) {
					person.age++;
				}
			}
		};
		Runnable t2 = new Runnable() {

			@Override
			public void run() {
				for (int j = 0; j < 1000000; j++) {
					person.age++;
				}

			}
		};

		new Thread(t1).start();
		new Thread(t2).start();
		Thread.sleep(3000);
		System.out.println(person.age);
	}

	/**
	 * 多线程赋值操作
	 * volatile 关键字修饰对象时，对象中的属性也不存在可见性问题了
	 */
	@Test
	public void t3() throws InterruptedException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				while (!person.flag) {
					
				}
				System.out.println("r1 执行结束");
			}
		};
		Thread t1 = new Thread(r1);
		t1.start();
		Thread.sleep(1000);
		person.flag = true;
		t1.join();
	}

}
