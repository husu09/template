package com.su.container;

import java.util.TreeMap;

import org.junit.Test;
/**
 * 测试 TreeMap 的排序规则，按从小到时大排列
 * firstkey 最小值
 * lastKey 最大值
 * */
public class TreeMapTest {
	public static void main(String[] args) {
		TreeMap<Long, Integer> map = new TreeMap<>();
		map.put(1L, 3);
		map.put(3L, 2);
		map.put(2L, 1);
		System.out.println(map);
		System.out.println(map.firstKey());
		System.out.println(map.lastKey());
	}
	
	@Test
	public void t1() {
		TreeMap<Long, Integer> map = new TreeMap<>();
		//System.out.println(map.lastKey()); 没有值时报错
		//System.out.println(map.lastEntry());
		//System.out.println(map.pollLastEntry());
	}
}
