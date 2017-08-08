package com.su.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author husu
 * Condition 的使用要配合 lock 才行
 */
public class ConditionDemo implements Runnable {
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	
	public static void main(String[] args) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.lock();
		condition.signalAll();
		lock.unlock();
	}

	@Override
	public void run() {
		lock.lock();
		try {
			// 等待
			condition.await();
			System.out.println(Thread.currentThread().getName() + " 执行成功");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
}
