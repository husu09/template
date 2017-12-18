package com.su.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 快速排序
 */
public class QuickSort {
	public static Integer[] sort(Integer[] arr) {
		//基线 条件： 为 空 或 只 包含 一个 元素 的 数组 是“ 有序” 的
		if (arr.length < 2)
			return arr;
		int index = arr.length / 2;
		int pivot = arr[index];
		//由 所有 小于 等于 基准 值 的 元素 组成 的 子 数组
		List<Integer> less = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= pivot && i != index)
				less.add(arr[i]);
		}
		//由 所有 大于 基准 值 的 元素 组成 的 子 数组
		List<Integer> greater = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > pivot)
				greater.add(arr[i]);
		}
		return concat(sort(less.toArray(new Integer[less.size()])), new Integer[] { pivot },
				sort(greater.toArray(new Integer[greater.size()])));
	}

	public static Integer[] concat(Integer[]... arrs) {
		int totalLength = 0;
		for (Integer[] arr : arrs) {
			totalLength += arr.length;
		}
		Integer[] result = new Integer[totalLength];
		int i = 0;
		for (Integer[] arr : arrs) {
			for (Integer v : arr) {
				result[i] = v;
				i++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 4, 78, 345, 48, 321, 4543, 489, 12349, 156 };
		arr = sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
