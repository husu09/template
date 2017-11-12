package com.su.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.junit.Test;

public class Math {

	/**
	 * 在一维数组中，存储坐标
	 */
	@Test
	public void t1() {
		// 10 * 10 的格子
		int scale = 10;
		int[] arr = new int[scale * scale];
		// 在数组中存储坐标
		int index = 0;
		for (int x = 0; x < scale; x++) {
			for (int y = 0; y < scale; y++) {
				arr[index] = x * scale + y;
				index++;
			}
		}
		System.out.println(Arrays.toString(arr));

		// 取出数组中的坐标
		for (int i = 0; i < arr.length; i++) {
			int x = arr[i] / scale;
			int y = arr[i] % scale;
			System.out.println(x + "," + y);
		}
	}

	/**
	 * 根据坐标计算索引，在索引用存储数据
	 */
	@Test
	public void t2() {
		// 10 * 10 的格子
		int scale = 10;
		String[] arr = new String[scale * scale];
		for (int x = 0; x < scale; x++) {
			for (int y = 0; y < scale; y++) {
				arr[x * scale + y] = x + "," + y;
			}
		}
		System.out.println(Arrays.toString(arr));

	}

	/**
	 * 按概率随机/错误
	 */
	@Test
	public void t3() {
		Map<String, Integer> map = new HashMap<>();
		map.put("绿色品质", 40);
		map.put("蓝色品质", 30);
		map.put("紫色品质", 15);
		map.put("粉色品质", 10);
		map.put("史诗品质", 5);

		Random random = new Random();
		Map<String, Integer> statistics = new HashMap<>();
		// 随机100次
		for (int i = 0; i < 100; i++) {
			for (Entry<String, Integer> e : map.entrySet()) {
				int r = random.nextInt(100);
				if (r >= 100) {
					System.out.println(r);
				}
				if (r < e.getValue()) {
					if (statistics.containsKey(e.getKey())) {
						statistics.put(e.getKey(), statistics.get(e.getKey()) + 1);
					} else {
						statistics.put(e.getKey(), 1);
					}
					continue;
				}
			}
		}
		System.out.println(statistics);

	}
	
	/**
	 * 按概率随机/正确
	 * */
	@Test
	public void t4() {
		Map<String, Integer> map = new HashMap<>();
		map.put("2500", 2500);
		map.put("1500", 1500);
		map.put("1000", 1000);
		map.put("4500", 4500);
		map.put("500", 500);
		Random random = new Random();
		Map<String, Integer> statistics = new HashMap<>();
		// 随机100次
		for (int i = 0; i < 1; i++) {
			int totalCount = 0;
			int rate = random.nextInt(10000) + 1;
			
			for (Entry<String, Integer> e : map.entrySet()) {
				totalCount += e.getValue();
				if ( rate <= totalCount) {
					if (statistics.containsKey(e.getKey())) {
						statistics.put(e.getKey(), statistics.get(e.getKey()) + 1);
					} else {
						statistics.put(e.getKey(), 1);
					}
					break;
				}
			}
		}
		System.out.println(statistics);
	}
	
	/**
	 * 比率是在一个数轴上的正态分布
	 * */
	@Test
	public void t5() {
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("2500", 2500);
		map.put("1500", 1500);
		map.put("1000", 1000);
		map.put("4500", 4500);
		map.put("500", 500);
		int realRate = 0;
		for (Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + " : " + (realRate += e.getValue()));
		}
	}
	
	/**
	 * 随机不重复的值
	 * */
	@Test
	public void t6() {
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("2500", 2500);
		map.put("1500", 1500);
		map.put("1000", 1000);
		map.put("4500", 4500);
		map.put("500", 500);
		
		// 保存抽中的元素
		List<String> list = new ArrayList<>(map.size());
		Random random = new Random();
		
		for (int i = 0; i < map.size(); i++) {
			// 计算数轴长度，每抽中一个元素，缩短数轴减少相应的区间
			int maxLength = 0;
			for (Entry<String, Integer> e : map.entrySet()) {
				if (list.contains(e.getKey()))
					continue;
				maxLength += e.getValue();
			}
			
			
			int rate = random.nextInt(maxLength) + 1;
			int realRate = 0;
			for (Entry<String, Integer> e : map.entrySet()) {
				// 跳过已经抽中过的区间
				if (list.contains(e.getKey()))
					continue;
				if (rate <= (realRate += e.getValue())) {
					list.add(e.getKey());
					break;
				}
			}
		}
		
		System.out.println(list);
	}

}
