package com.su.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author husu
 * 生产者消费者模式
 */
public class ProducerAndConsumer {
	/**
	 * @author husu
	 * 生产者
	 */
	public static class Producer implements Runnable  {
		
		private BlockingQueue<String> queue;
		
		
		public Producer(BlockingQueue<String> queue) {
			this.queue = queue;
		}


		@Override
		public void run() {
			for(;;) {
				try {
					Thread.sleep(2500);
					queue.put(String.valueOf(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * @author husu
	 * 消费者
	 */
	public static class Consumer implements Runnable  {
		
		private BlockingQueue<String> queue;
		
		
		public Consumer(BlockingQueue<String> queue) {
			this.queue = queue;
		}


		@Override
		public void run() {
			for(;;) {
				try {
					Thread.sleep(5000);
					String take = queue.take();
					System.out.println("消费" + take);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
		
		ExecutorService executor = Executors.newFixedThreadPool(20);
		executor.submit(new Producer(queue));
		executor.submit(new Consumer(queue));
	}
}
