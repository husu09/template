package com.su.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author husu
 * CyclicBarrier 等待一批线程全部到达再执行 
 */
public class CyclicBarrierDemo implements Runnable{
	private static CyclicBarrierDemo instance = new CyclicBarrierDemo();
	private static CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
		
		@Override
		public void run() {
			System.out.println("barrierAction");
		}
	});

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 集合");
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 执行");
		
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(instance, "t" + i).start();
		}
		
	}
}
