package com.su.container;

import java.util.HashSet;
import java.util.Set;

/**
 * 删除一个元素返回新的集合?
 * 没有api需要自己写^_^
 * */
public class DeleteElemnetReturnNewCollection {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.remove(1);
		System.out.println(set);
	}
}
