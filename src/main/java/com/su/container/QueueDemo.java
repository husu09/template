package com.su.container;

import java.util.PriorityQueue;

import org.junit.Test;

/**
 * @author husu
 * 队列
 * 
 * 入队
 * add()
 * offer()	使用有界队列时，该方法通常比 add() 好	
 * 
 * 出队
 * poll()		删除，无元素时返回 null
 * peek()		不删除，无元素时返回 null
 * remove()		删除，无元素时抛异常
 * element()	不删除，无元素时抛异常
 *
 */
public class QueueDemo {
	
	/**
	 * priorityQueue 不允许插入 null 值
	 * 使用 Comparable 按 从小到大的排序出列
	 */
	@Test
	public void priorityQueue() {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(0);
		queue.add(-1);
		// [-1, 0, 3, 2, 1]
		System.out.println(queue);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		
	}
}
