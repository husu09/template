package com.su.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class FutureDemo {

	/**
	 * @author husu 
	 * 返回数据的接口
	 */
	public static interface Data {
		String getResult();
	}

	/**
	 * @author husu 
	 * Future数据，构造很快，但是是一个虚拟的数据，需要装配RealData
	 */
	public static class FutureData implements Data {

		protected RealData realData; // FutureData 是RealData的包装
		protected boolean isReady;

		@Override
		public synchronized String getResult() {
			while (!isReady) { // 会等待RealData构造完成
				try {
					wait(); // 一直等待，知道RealData被注入
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return realData.getResult(); // 由RealData实现
		}

		public synchronized void setRealData(RealData realData) {
			if (isReady) {
				return;
			}
			this.isReady = true;
			this.realData = realData;
			notifyAll(); // RealData已经被注入，通知getResult()
		}

	}

	/**
	 * @author husu 
	 * 真实数据，其构造是比较慢的
	 */
	public static class RealData implements Data {

		protected final String result;

		public RealData(String para) {
			// RealData的构造可能很慢，需要用户等待很久，这里使用sleep模拟
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 10; i++) {
				sb.append(para);
				try {// 这里使用sleep，代替一个很慢的操作过程
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.result = sb.toString();
		}

		@Override
		public String getResult() {
			return result;
		}

	}

	/**
	 * @author husu 返回Data对象，立即返回FutureData，并开启ClientThread线程装配RealData
	 */
	public static class Client {

		public Data request(final String queryStr) {
			final FutureData future = new FutureData();
			new Thread() {
				public void run() {// RealData的构建很慢，
					// 所以在单独的线程中进行
					RealData realdata = new RealData(queryStr);
					future.setRealData(realdata);
				}
			}.start();
			return future;// FutureData会被立即返回
		}
	}

	/**
	 * @param args
	 * 系统启动，调用Client发出请求
	 */
	public static void main(String[] args) {
		Client client = new Client();
		// 这里会立即返回，因为得到的是FutureData而不是RealData
		Data data = client.request("name");
		System.out.println("请求完毕");
		try {// 这里可以用一个sleep代替了对其他业务逻辑的处理
				// 在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 使用真实的数据
		System.out.println("数据 = " + data.getResult());
	}
	
	/**
	 * java 中的 future 模式，使用 FutureTask 的方式
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Test
	public void future() throws InterruptedException, ExecutionException {
		FutureTask<String> task = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				return "hello java future";
			}
			
		});
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(task);
		System.out.println(task.get());
	}
	
	/**
	 * java 中的 future 模式，直接使用 FutureTask 的方式
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void future_() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<String> future = executor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				return "hello java future";
			}
			
		});
		System.out.println(future.get());
	}
}
