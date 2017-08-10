package com.su.concurrency.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author husu
 * 并发容器
 * CopyOnWriteArrayList 适用于读多写少，底层实现并发的原理是数组复制，当写操作时复制一份数据并修改，修改完成后改变集合的引用
 * ConcurrentHashMap 分段原理，默认分为16段
 * 阻塞队列 
 * ArrayBlockingQueue 有界队列，读写未分离
 * LinkedBlockingQueue 无界队列，读写分离效率高
 * SynchronousQueue 无缓冲队列，没有容量，当一个线程调用 take 方法等待时，另一个线程才能 add 
 * PriorityBlockingQueue 优先级队列，对象需要实现 Comparable 接口
 * DelayQueue 延时队列，元素需要实现 Delayed 接口
 * ----------------------------------------------------------------
 * |	 | 可以把异常 | 返回布尔值 | 可以阻塞 | 设定等待时间                         |
 * | 入队 | add(e)   | offer(e) | put(e)  | offer(e, timeout, unit)|
 * | 出队 | remove() | poll()   | take()  | poll(timeout, unit)    |
 * | 查看 | element()| peek()   |         |                        |
 * ----------------------------------------------------------------
 */
public class ContainerDemo {
	@Test
	public void copyOnWriteArrayList() {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	}
	
	@Test
	public void concurrentHashMap() {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
	}
	
	@Test
	public void arrayBlockingQueue() {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
	}
	
	@Test
	public void linkedBlockingQueue() {
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
	}
	
	@Test
	public void synchronousQueue() throws InterruptedException {
		SynchronousQueue<Integer> queue = new SynchronousQueue<>();
		// 报错
		// queue.add(1);
	}
	
	@Test
	public void priorityBlockingQueue() {
		PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
		queue.add(1);
		queue.add(2);
		queue.add(0);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
	
	private static class Person implements Delayed {
		// 结束时间
		private long endTime;
		private String name;
		
		public Person(String name, long endTime) {
			this.name = name;
			this.endTime = endTime;
		}
		
		@Override
		public int compareTo(Delayed o) {
			Person p = (Person) o;
			return this.endTime > p.endTime ? 1 : this.endTime < p.endTime ? -1 : 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(endTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		@Override
		public String toString() {
			return "Person [name=" + name + "]";
		}
		
		
	}
	
	/**
	 * 延时队列
	 */
	@Test
	public void delayQueue() {
		DelayQueue<Person> queue = new DelayQueue<>();
		queue.put(new Person("p1", 1000 * 5 + System.currentTimeMillis()));
		queue.put(new Person("p2", 1000 * 10 + System.currentTimeMillis()));
		while (true) {
			Person p = null;
			if ((p = queue.poll()) != null) {
				System.out.println(p);
			}
		}
	}
}
