package com.su.concurrency.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;


public class SecureListCompare {
	
	public void compute (Collection<Integer> coll) {
		long start = System.currentTimeMillis();
		// 生产者
		Runnable producer = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10000000; i++) {
					coll.add(1);
				}
				
			}
		};
		// 消费者
		Runnable consumer = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10000000; i++) {
					coll.remove(1);
				}
			}
		};
		
		ExecutorService pool = Executors.newFixedThreadPool(20);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(producer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.execute(consumer);
		pool.shutdown();
		
		while (true) {
			if (pool.isTerminated()) {
				break;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(coll.getClass().getSimpleName() + " " + coll + " " + ((end - start)/60) + "s");
	}
	
	public static void main(String[] args) {
		SecureListCompare sc = new SecureListCompare();
		sc.compute(new ConcurrentLinkedQueue<>());
		//sc.compute(new ConcurrentSkipListSet<>());
		sc.compute(new ConcurrentLinkedDeque<>());
		//在读写频繁的情况下，慢到爆炸
		//sc.compute(new CopyOnWrite<>());
		//sc.compute(new CopyOnWriteArraySet<>());
		//sc.compute(new Vector<>());
		//sc.compute(Collections.synchronizedCollection(new ArrayList<>()));
	}
	
	@Test
	public void t1() {
		Collection<Integer> c = new ArrayList<>();
		c.add(1);
		c.add(2);
		c.add(1);
		// List 只会删除第一个匹配的元素
		c.remove(1);
		System.out.println(c);
	}
	
}
