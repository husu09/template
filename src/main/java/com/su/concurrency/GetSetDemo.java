package com.su.concurrency;

import org.junit.Test;

public class GetSetDemo {
	private Person p = new Person();
	private int temp;
	
	/**
	 * get/set方法不同步，好像也没什么问题
	 * */
	@Test
	public void t1() throws InterruptedException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				for (long l = 0; l < Integer.MAX_VALUE * 4L; l++) {
					
				}
				p.setAge(1);
				System.out.println(Thread.currentThread().getName() + " 设置 p.age = " + 1);
				while (true) {
					
				}
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				while (p.getAge() == 0) {
					/*System.out.println(Thread.currentThread().getName() + " 获取 p.age = " + p.getAge());
					for (long l = 0; l < Integer.MAX_VALUE * 4L; l++) {
						
					}*/
				}
				System.out.println("结束");
			}
		};
		Thread t1 = new Thread(r1, "t1");
		Thread t2 = new Thread(r2, "t2");
		t2.start();
		Thread.sleep(100);
		t1.start();
		t1.join();
		t2.join();
	}
	
	/**
	 * 不使用get/set方法，直接操作属性
	 * */
	@Test
	public void t2() throws InterruptedException {
		Runnable r1 = new  Runnable() {
			
			@Override
			public void run() {
				while (p.age == 0) {
					/*for (long l = 0; l < Integer.MAX_VALUE * 4L; l++) {
						
					}*/
					//System.out.println(p.age);
				}
				System.out.println("设置属性结束");
			}
		};
		
		Thread t1 = new Thread(r1);
		t1.start();
		Thread.sleep(1000);
		System.out.println("开始设置属性");
		p.age = 1;
		t1.join();
		
	}
	
	/**
	 * 不使用set方法，直接操作属性
	 * */
	@Test
	public void t3() throws InterruptedException {
		Runnable r1 = new  Runnable() {
			
			@Override
			public void run() {
				while (p.getAge() == 0) {
					
				}
				System.out.println("设置属性结束");
			}
		};
		
		Thread t1 = new Thread(r1);
		t1.start();
		Thread.sleep(1000);
		System.out.println("开始设置属性");
		p.age = 1;
		t1.join();
	}
	
	/**
	 * 使用get/set方法，直接操作属性
	 * */
	@Test
	public void t4() throws InterruptedException {
		Runnable r1 = new  Runnable() {
			
			@Override
			public void run() {
				while (p.getAge() == 0) {
					
				}
				System.out.println("设置属性结束");
			}
		};
		
		Thread t1 = new Thread(r1);
		t1.start();
		Thread.sleep(1000);
		System.out.println("开始设置属性");
		p.setAge(1);
		t1.join();
	}
	/************************************************* 操作 boolean 类型 ********************************************************/
	/**
	 * 存在可见性问题
	 * */
	@Test
	public void t5() throws InterruptedException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				while (!p.flag) {
					
				}
				System.out.println("结束");
			}
		};
		Thread t1 = new Thread(r1);
		t1.start();
		Thread.sleep(1000);
		p.flag = true;
		t1.join();
		
	}
	
	/**
	 * 存在可见性问题，get方法加锁可以保证同步性
	 * */
	@Test
	public void t6() throws InterruptedException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				while (p.isFlag() == false) {
					
				}
				System.out.println("结束");
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				p.setFlag(true);
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		Thread.sleep(1000);
		t2.start();
		t1.join();
	}
	/************************************************* 操作 int 类型 ********************************************************/
	/**
	 * 存在可见性问题
	 * */
	@Test
	public void t7() throws InterruptedException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				while (p.getAge() == 0) {
					for (long l = 0; l < Integer.MAX_VALUE * 1L; l++) {
						
					}
				}
				System.out.println("结束");
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				p.setAge(1);
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		Thread.sleep(1000);
		t2.start();
		t1.join();
	}
}
class Person {
	public int age;
	public boolean flag;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
}
