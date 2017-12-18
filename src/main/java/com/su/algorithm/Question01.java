package com.su.algorithm;

import java.util.Arrays;

import org.junit.Test;

/**
 * 将一个矩形均分成最大可容纳的正方形
 * 1680 * 640
 * (1) 找出基线条件		
 * (2) 不断将问题分解
 * */
public class Question01 {
	
	private int[] resolve(int[] arr) {
		int newArr[]  = new int[arr.length];
		newArr[0] = arr[1];
		newArr[1] = arr[0]%arr[1];
		if (newArr[1] == 0) {
			return newArr;
		}
		return resolve(newArr);
	}
	
	@Test
	public void t1() {
		int[] arr = resolve(new int[]{1680, 640});
		System.out.println(Arrays.toString(arr));
		/**
		 * 过程
		 * 1680 % 640 = 400
		 * 640 % 400 =  240
		 * 400 % 240 = 160
		 * 240 % 160 = 80
		 * 160 % 80 = 0 return
		 * */
	}
}
