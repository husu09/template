package com.su.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * @author husu 
 * 可重入锁
 */
public class ReentrantLockDemo {
	/**
	 * ReentrantLock，可重入性
	 */
	@Test
	public void reentrantLock_1() {
		Runnable r = new Runnable() {
			private int i = 0;
			ReentrantLock lock = new ReentrantLock();
			@Override
			public void run() {
					lock.lock();
					lock.lock();
					try {
						for (int j = 0; j < 10; j++, i++);
						System.out.println(this + " : " + Thread.currentThread().getName() + " : " + i);
					} finally {
						lock.unlock();
						lock.unlock();
					}
				
			}
		};
		Thread t1 = new Thread(r, "t1");
		Thread t2 = new Thread(r, "t2");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ReentrantLock，lockInterruptibly() 优先响应中断再响应锁获取，可以在等待获取锁时被中断，当线程等待获取锁时状态是 WAITING
	 * Thread.interrupt()方法可用于中断指定线程，但线程并不是在任何状态下可被立即中断，
	 * 一般只有当线程处于阻塞状态（调用wait(),join(),sleep()等方法）时才会被立即中断，
	 * 如果线程处于不可被立即中断状态，那么Thread.interrupt()只会标志线程的中断状态，
	 * 以便后续操作用于判断，即isInterrupted()方法会返回true.
	 */
	@Test
	public void reentrantLock_2() {
		ReentrantLock lock = new ReentrantLock();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					lock.lockInterruptibly();
					for(;;);
				} catch (InterruptedException e) {
					//e.printStackTrace();
					System.out.println("线程被中断");
				} finally {
					if (lock.isHeldByCurrentThread()) {
						lock.unlock();
					}
				}
				
				
			}
		};
		Thread t1 = new Thread(r, "t1");
		Thread t2 = new Thread(r, "t2");
		t1.start();
		try {
			Thread.sleep(1000);
			t2.start();
			Thread.sleep(1000);
			System.out.println(t2.getState());
			t2.interrupt();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 与 lock.lockInterruptibly() 的比较，线程在等待锁时调用 t.interrupt() 不会响应中断 
	 */
	@Test
	public void reentrantLock_3() {
		
	}
	
	/**
	 * 公平性，设置 ReentrantLock 使用公平锁，先申请锁的先获得锁，公平锁性能比非公平锁性能低，默认使用非公平锁
	 */
	@Test
	public void reentrantLock_4() {
		ReentrantLock lock = new ReentrantLock(true);
	}
	
	/**
	 * lock.tryLock() 时限性
	 */
	@Test
	public void reentrantLock_5() {
		Runnable r = new Runnable() {
			ReentrantLock lock = new ReentrantLock();
			@Override
			public void run() {
				try {
					// 尝试获取锁并优先响应中断异常
					if (lock.tryLock(5, TimeUnit.SECONDS)) {
						Thread.sleep(6000);
					} else {
						System.out.println(Thread.currentThread().getName() + " 尝试获取锁失败");
					}
					// 一直尝试
					//lock.tryLock();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// 如果获得了锁，则释放
					if (lock.isHeldByCurrentThread())
						lock.unlock();
				}
			}
		};
		Thread t1 = new Thread(r, "t1");
		Thread t2 = new Thread(r, "t2");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// TODO 检查死锁
}
