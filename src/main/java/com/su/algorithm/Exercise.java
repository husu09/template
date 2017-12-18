package com.su.algorithm;

import org.junit.Test;

public class Exercise {
	/**
	 * 找出列表中的最大数字
	 * */
	private int findMaxNum(int[] arr) {
		int[] newArr = new int[arr.length - 1]; 
		if (arr.length == 1)
			return arr[0];
		if (arr[0] > arr[1]) {
			arr[1] = arr[0];
		}
		System.arraycopy(arr, 1, newArr, 0, arr.length - 1);
		return findMaxNum(newArr);
	}
	
	@Test
	public void t1() {
		int max = findMaxNum(new int[]{1,3,6,8,0});
		System.out.println(max);
	}
	/**
	 * 二分查找
	 * */
}
