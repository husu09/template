package com.su.concurrency.question;

/**
 * @author husu
 * 多线程下无锁改变对象引用，初步判断安全
 *
 */
public class ObjectReference {
	private static Object o;
	
	public static void main(String[] args) throws Exception {
		// t1线程不断赋值
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					o = new Object();
					System.out.println("set : " + o);
				}
			}
		}, "t1").start();
		
		Thread.sleep(5000);
		
		// t2线程不断取值
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("get : " + o);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t2").start();
		
		
	}
	
}
