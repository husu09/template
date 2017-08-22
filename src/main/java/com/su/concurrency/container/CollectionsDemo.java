package com.su.concurrency.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author husu
 * Collections 工具类可以用来包装并发容器，但是内部实现是使用 synchronized，所以并不是一个高效的
 */
public class CollectionsDemo {
	public static void main(String[] args) {
		List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
		Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
	}
}
