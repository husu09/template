package com.su.concurrency.question;

/**
 * @author husu
 * 多线程可见性问题
 */
public class NoVisibility {
	
	private static boolean ready;
	private static int number;

	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while (!ready) {
				//Thread.yield(); 如果执行这条语句，不会出现预期结果。猜测是因为，yield失出cpu执行时间再重新获得cpu执行时间时会同步主内存中的数据
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ReaderThread().start();
		Thread.sleep(10);
		number = 42;
		ready = true;
	}
}