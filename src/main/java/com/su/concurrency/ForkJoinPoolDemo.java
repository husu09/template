package com.su.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author husu
 * ForkJoinPool 将一个大任务分解成小任务执行，各小任务执行完成后再合并结果
 * RecursiveTask 有返回值的
 * RecursiveAction 无返回值的
 */
public class ForkJoinPoolDemo extends RecursiveTask<Integer> { 
	private int[] arr;
	private int start;
	private int end;
	
	public ForkJoinPoolDemo(int[] arr, int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// 以 20 个元素分组
		if (end - start < 20) {
			for (int i = start; i < end; i++) {
				sum += arr[i];
			}
			return sum;
		} else {
			// 大于 20 个元素的再次分组
			int middle = (start + end)/2;
			ForkJoinPoolDemo left = new ForkJoinPoolDemo(arr, start, middle);
			System.out.println("start : " + start + ", end : " + middle);
			ForkJoinPoolDemo right = new ForkJoinPoolDemo(arr, middle, end);
			System.out.println("start : " + middle + ", end : " + end);
			left.fork();
			right.fork();
			return left.join() + right.join();
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 1; i <= arr.length; i++) {
			arr[i-1] = i;
		}
		ForkJoinPool pool = new ForkJoinPool();
		// 累加数组中的所有元素
		ForkJoinTask<Integer> result = pool.submit(new ForkJoinPoolDemo(arr, 0, arr.length));
		try {
			int sum = result.get();
			System.out.println(sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}
