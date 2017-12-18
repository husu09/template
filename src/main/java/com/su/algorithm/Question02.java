package com.su.algorithm;

import org.junit.Test;

/**
 * 使用递归方式计算数组的总和
 * */
public class Question02 {
	
	private int resolve(int[] arr) {
		if (arr.length == 1) {
			return arr[0];
		}
		int[] newArr = new int[arr.length - 1];
		System.arraycopy(arr, 1, newArr, 0, arr.length - 1);
		return arr[0] + resolve(newArr);
	}
	
	@Test
	public void t1() {
		int sum = resolve(new int[]{2,4,6});
		System.out.println(sum);
		/**
		 * 过程
		 * resolve(2,4,6)
		 * 2 + resolve(4,6)
		 * 4 + resolve(6)
		 * resolve(6) return
		 * */
	}
	
}
