package com.su.concurrency.question;

public class RunThread extends Thread {
	private boolean isRunnig = true;

	public void setRunnig(boolean isRunnig) {
		this.isRunnig = isRunnig;
		while(true) {
			
		}
	}

	@Override
	public void run() {
		System.out.println("执行开始");
		while (isRunnig == true) {
			Thread.yield();
		}
		System.out.println("执行结束");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(1000);
		rt.setRunnig(false);
		System.out.println("isRunnig的值已经被设置了false");
		
	}
}
