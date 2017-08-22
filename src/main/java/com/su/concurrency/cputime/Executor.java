package com.su.concurrency.cputime;

public class Executor {
	
	private boolean flag;
	
	public void read() {
		while (true) {
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				
			}
			System.out.println(Thread.currentThread().getName() + "打印" + flag);
		}
	}
	
	public void write() {
		flag = true;
		System.out.println(Thread.currentThread().getName() + "修改标记为true");
	}
}
