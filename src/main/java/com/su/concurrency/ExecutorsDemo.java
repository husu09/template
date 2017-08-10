package com.su.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * @author husu
 * 线程池
 * 	Executors.newSingleThreadExecutor();
 * 	Executors.newCachedThreadPool();
 * 	Executors.newFixedThreadPool(10);
 * 	Executors.newScheduledThreadPool(10);
 * 	Fixed Single Fixed 内部使用的都是无界队列 LinkQueue，Cached 内部的线程最大数量是 Integer.MAX ，
 * 	当在任务高负载的情况下会占用大量内存，所以更合理的办法是使用有限制的自定义线程池
 * 
 * 自定义线程池
 * 
 * 线程池扩展
 * 
 * 线程池拒绝策略
 * 	当待执行队列满时，再往里添加任务时就会执行拒绝策略
 * 	AbortPolicy 抛出异常
 * 	CallerRunsPolicy 由调用的线程自己执行任务
 *  DiscardOldestPolicy 丢弃队列中第一个任务，执行新任务
 *  DiscardPlicy 不做任务操作
 *  自定义拒绝策略，可以写日志或做其它处理 
 * 
 * 线程池工厂方法
 */
public class ExecutorsDemo {
	/**
	 * 所有线程池
	 */
	@Test
	public void allThreadPool() {
		// 一个线程的线程池
		Executors.newSingleThreadExecutor();
		// 一个自动扩张、收缩的线程池
		Executors.newCachedThreadPool();
		// 固定长度的线程池
		Executors.newFixedThreadPool(10);
		// 一个定时任务的线程池
		Executors.newScheduledThreadPool(10);
	}
	
	/**
	 * 定时任务的线程池使用
	 */
	@Test
	public void scheduledPool() {
		CountDownLatch latch = new CountDownLatch(1);
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
		// 固定频率执行，当任务时间超过间隔时间不会新启动线程执行
		pool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				//System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) +  " 执行");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 2, 2, TimeUnit.SECONDS);
		
		// 每次任务完成后间隔多少秒再执行
		pool.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) +  " 执行");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 2, 2, TimeUnit.SECONDS);
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 自定义线程池、扩展、拒绝策略
	 */
	@Test
	public void threadPool() {
		/**
		 * corePoolSize 核心线程数量 
		 * maximumPoolSize 最大线程数量
		 * keepAliveTime 空闲线程保持多长时间
		 * workQueue 待执行的任务队列
		 * */
		new ThreadPoolExecutor(20, 50, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactory() {
			
			private AtomicInteger i = new AtomicInteger();
			//线程池工厂，可以在里面做一些线程的自定义操作
			@Override
			public Thread newThread(Runnable r) {
				ThreadGroup g = new ThreadGroup("group");
				Thread t = new Thread(g, r, "t" + i.incrementAndGet());
				t.setDaemon(true);
				return t;
			}
		}, new RejectedExecutionHandler() {
			
			// 自定义拒绝策略
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println(r + "执行失败");
			}
		}) {
			// 线程扩展
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("任务开始前");
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("任务执行后");
			}

			@Override
			protected void terminated() {
				System.out.println("线程关闭后");
			}
			
		};
	}
}
