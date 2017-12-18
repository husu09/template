package com.su.algorithm;

import java.util.Arrays;

import org.junit.Test;

/**
 * 选择排序
 * */
public class SelectionSort {
	

	public int[] selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length - 1; j++) {
				if (arr[i] > arr[j + 1]) {
					int temp = arr[i];
					arr[i] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}
	
	@Test
	public void t1() {
		int[] arr = {2,6,8,9,1,0,-3};
		arr = selectionSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
