package com.su.concurrency;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author husu
 * MasterWoker 模式
 * Master 分配任务，汇总计算结果
 */
public class MasterWokerDemo {
	
	/**
	 * @author husu
	 * 任务
	 */
	static public class Task {
		private int id;
		private int price;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		
		
	}
	/**
	 * @author husu
	 * 控制
	 */
	static public class Master {
		// 1 工作者
		private HashMap<Integer, Thread> wokerMap = new HashMap<>();
		// 2 任务
		private ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<>();
		// 3运行结果
		private ConcurrentHashMap<Integer, Integer> result = new ConcurrentHashMap<>();
		
		public Master(Woker woker, int num) {
			woker.setTasks(tasks);
			woker.setResult(result);
			for (int i = 0; i < num; i++) {
				wokerMap.put(i, new Thread(woker));
			}
		}
		
		// 4开始执行
		public void execute() {
			for(Entry<Integer, Thread> e : wokerMap.entrySet()) {
				e.getValue().start();
			}
		}
		
		// 5是否完成
		public boolean isComplete() {
			for(Entry<Integer, Thread> e : wokerMap.entrySet()) {
				if (e.getValue().getState() != Thread.State.TERMINATED) {
					return false;
				}
			}
			return true;
		}
		
		// 6获取结果
		public int getResult() {
			int res = 0;
			for(Entry<Integer, Integer> e : result.entrySet()) {
				res += e.getValue();
			}
			return res;
		}
		
		// 7提交任务
		public void submit(Task task) {
			tasks.offer(task);
		}
		
	}
	
	/**
	 * @author husu
	 * 工作者
	 */
	static public class Woker implements Runnable {
		// 2 任务
		private ConcurrentLinkedQueue<Task> tasks;
		// 3运行结果
		private ConcurrentHashMap<Integer, Integer> result;
		
		public void setTasks(ConcurrentLinkedQueue<Task> tasks) {
			this.tasks = tasks;
		}

		public void setResult(ConcurrentHashMap<Integer, Integer> result) {
			this.result = result;
		}

		@Override
		public void run() {
			while (true) {
				Task t = tasks.poll();
				if (t == null) break;
				try {
					//任务操作
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				result.put(t.getId(), t.getPrice());
			}
		}
	}
	
	public static void main(String[] args) {
		Master m = new Master(new Woker(), 20);
		for (int i = 1; i <= 100; i++) {
			Task t = new Task();
			t.setId(i);
			t.setPrice(i);
			m.submit(t);
		}
		m.execute();
		long start = System.currentTimeMillis();
		// 判断Worker任务是否完成
		while (!m.isComplete());
		long end = System.currentTimeMillis();
		System.out.println("执行结果：" + m.getResult());
		System.out.println("执行时间：" + (end - start));
	}
}
