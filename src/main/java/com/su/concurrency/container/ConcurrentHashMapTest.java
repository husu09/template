package com.su.concurrency.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class ConcurrentHashMapTest {
	public static class Person {
		private int age;
		private String name;
		public Person(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}
		@Override
		public String toString() {
			return "Person [age=" + age + ", name=" + name + "]";
		}
		
	}
	public static void main(String[] args) {
		// Map是无序的
		Map<Person, Integer> map = new ConcurrentHashMap<>();
		map.put(new Person(1, "a"), 1);
		map.put(new Person(2, "b"), 2);
		map.put(new Person(3, "c"), 3);
		map.put(new Person(4, "d"), 4);
		map.put(new Person(5, "e"), 5);
		for (Person p : map.keySet()) {
			System.out.println(p);
		}
	}
	
	@Test
	public void t1() {
		// 如果是整型做为key,底部的hash算法应该和排序一致
		Map<Long, Long> map = new ConcurrentHashMap<>();
		map.put(6L, 6L);
		map.put(4L, 4L);
		map.put(1L, 1L);
		map.put(2L, 2L);
		map.put(3L, 3L);
		map.put(5L, 5L);
		for (Iterator<Long> it = map.keySet().iterator(); it.hasNext();) {
			map.remove(it);
			break;
		}
		System.out.println(map);
	}
	
	@Test
	public void t2() {
		// 如果是整型做为key,底部的hash算法应该和排序一致
		Map<Long, Long> map = new ConcurrentHashMap<>();
		for (Long i = 1L ; i <= 5000 ; i++) {
			map.put(i, i);
		}
		for (Iterator<Long> it = map.keySet().iterator(); it.hasNext();) {
			map.remove(it);
			break;
		}
		System.out.println(map);
	}
	
	@Test
	public void t3() {
		//删除map中的元素
		Map<Integer,String> map = new ConcurrentHashMap<>();
		map.put(1, "小明");
		map.put(2, "小刚");
		map.put(3, "小红");
		/*for (Iterator<String> it = map.values().iterator(); it.hasNext();) {
			it.next();
			it.remove();
		}*/
		for (Iterator<Integer> it = map.keySet().iterator(); it.hasNext();) {
		 	it.next();
			it.remove();
		}
		System.out.println(map);
	}
}
