package com.su.algorithm;

import org.junit.Test;

/**
 * 二分查找
 */
public class BinarySearch {

	@Test
	public void t1() {
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int item = 8;
		int index = binarySearch(arr, item);
		System.out.println(index);
	}

	public int binarySearch(int[] arr, int item) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == item) {
				return mid;
			} else if (arr[mid] > item) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}

		}
		return -1;
	}

}
