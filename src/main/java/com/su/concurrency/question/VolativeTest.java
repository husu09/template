package com.su.concurrency.question;



public class VolativeTest implements Runnable{
	
	private static Object isRuning = null;
	
	@Override
	public void run() {
		System.out.println("执行开始");
		while (isRuning == null) {
		}
		System.out.println("执行结束");
	}
	
	public void stop() {
		isRuning = new Object();
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolativeTest t1 = new VolativeTest();
		new Thread(t1).start();
		Thread.sleep(2000);
		t1.stop();
		
	}

}
