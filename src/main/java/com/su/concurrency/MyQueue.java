package com.su.concurrency;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author husu 实现一个阻塞队列
 */
public final class MyQueue {
	/**
	 * 存储数据的容器
	 */
	private final LinkedList<Object> list = new LinkedList<>();

	/**
	 * 集合的上限
	 */
	private final int minSize = 0;
	/**
	 * 集合下限
	 */
	private final int maxSize;
	/**
	 * 计数器
	 */
	private final AtomicInteger count = new AtomicInteger();
	/**
	 * 锁
	 */
	private final Object lock = new Object();

	/**
	 * 获取队列当前元素
	 * 
	 * @return
	 */
	public int getSize() {
		return count.get();
	}

	public MyQueue(int size) {
		this.maxSize = size;
	}

	public void put(Object o) {
		try {
			synchronized (lock) {
				if (count.get() == maxSize) {
					lock.wait();
				}
				list.add(o);
				System.out.println("添加元素：" + o);
				count.incrementAndGet();
				lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Object take() {
		Object result = null;
		try {
			synchronized (lock) {
				if (count.get() == minSize) {
					lock.wait();
				}
				result = list.removeFirst();
				System.out.println("移除元素：" + result);
				count.decrementAndGet();
				lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MyQueue queue = new MyQueue(5);
			queue.put(1);
			queue.put(2);
			queue.put(3);
			queue.put(4);
			queue.put(5);
			new Thread("t1") {

				@Override
				public void run() {
					queue.put(6);
					queue.put(7);
				}

			}.start();
			TimeUnit.SECONDS.sleep(2);
			new Thread("t2") {

				@Override
				public void run() {
					queue.take();
					queue.take();
				}

			}.start();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
