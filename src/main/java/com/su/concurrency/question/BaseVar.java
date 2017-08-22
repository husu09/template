package com.su.concurrency.question;

/**
 * @author husu
 * 多线程下的基础变量赋值，初步判断安全
 *
 */
public class BaseVar {
	private static int i = 0;
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						i = 1;
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						System.out.println(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t2").start();
	}
}
