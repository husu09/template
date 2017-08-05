package com.su.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

/**
 * @author husu
 * Collection 接口是 List 、Set、Queue 接口的父接口
 * 
 */
public class CollectionDemo {
	@Test
	public void api() {
		Collection<String> c1 = new ArrayList<>();
		// 添加对象
		c1.add("a");
		//c1.addAll(c);
		
		// 清空
		c1.clear();
		
		// 是否包含
		c1.contains("a");
		//c1.containsAll(c);
		
		// 是否为空
		c1.isEmpty();
		
		// 删除对象
		// 删除第一个匹配的元素
		c1.remove("a");
		//c1.removeAll(c);
		
		// 取交集 删除集合 c 中不包含的元素
		//c1.retainAll(c);
		
		// 其它
		c1.iterator();
		c1.toArray();
		c1.size();
	}
	
	/**
	 * Collection 的 remove 方法只删除第一个匹配的元素
	 */
	@Test
	public void remove() {
		Collection<String> c = new ArrayList<>();
		c.add("a");
		c.add("a");
		
		c.remove("a");
		System.out.println(c); // [a]
	}
	
	/**
	 * Collection 的 removeAll 方法会删除所有匹配的元素
	 */
	@Test
	public void removeAll() {
		Collection<String> c = new ArrayList<>();
		c.add("a");
		c.add("a");
		
		Collection<String> c2 = new ArrayList<>();
		c2.add("a");
		
		c.removeAll(c2);
		System.out.println(c); // []
	}
	
	/**
	 * 使用 Iterator 接口遍历集合，Iterator 需要配合 Collection 使用
	 */
	@Test
	public void iterator() {
		Collection<String> c = new ArrayList<>();
		c.add("a");
		c.add("b");
		c.add("c");
		c.add("d");
		
		Iterator<String> iterator = c.iterator();
		while(iterator.hasNext()) {
			String s = iterator.next();
			if (s.equals("d")) {
				iterator.remove();
			}
		}
		System.out.println(c);
		// [a, b, c]
	}
	
	/**
	 * java.util.ConcurrentModificationException
	 * 使用 Iterator 遍历集合，不能在 Iterator 外改变集合
	 */
	@Test
	public void concurrentModificationException() {
		Collection<String> c = new ArrayList<>();
		c.add("a");
		c.add("b");
		c.add("c");
		c.add("d");
		
		Iterator<String> iterator = c.iterator();
		while(iterator.hasNext()) {
			String s = iterator.next();
			if (s.equals("d")) {
				c.remove("d");
			}
		}
		System.out.println(c);
		// [a, b, c]
	}
}
