package com.su.concurrency.cputime;

public class MyThread extends Thread {
	
	
	
	private Executor executor;
	
	public MyThread(Executor executor, String name) {
		super(name);
		this.executor = executor;
	}



	@Override
	public void run() {
		// 不退出
		while (true) {
			// 执行业务调用
			if (getName().equals("t1")) {
				executor.read();
			} else if (getName().equals("t2")) {
				executor.write();
				while (true) {
					
				}
			}
		}
	}
	
}
