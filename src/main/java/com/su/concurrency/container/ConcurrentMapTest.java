package com.su.concurrency.container;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * 测试map.replace
 * */
public class ConcurrentMapTest {
	/**
	 * 测试 replace
	 */
	public static void main(String[] args) {
		ConcurrentHashMap<Integer, Long> map = new ConcurrentHashMap<>();
		map.put(1, 1L);
		map.remove(1);
		Long l = map.replace(1, 2L);
		System.out.println(map);
		System.out.println(l);
	}
	
	/**
	 * 测试 remove
	 * */
	@Test
	public void t1() {
		ConcurrentHashMap<Integer, Long> map = new ConcurrentHashMap<>();
		map.put(1, 1111L);
		Long l = map.remove(1);
		System.out.println(l);
	}
	
	/**
	 * 测试 remove
	 * */
	@Test
	public void t2() {
		System.out.println(new Long(1) != 1);
	}
}
