package com.su.concurrency.container;

import java.util.concurrent.ConcurrentSkipListSet;

import org.junit.Test;

public class ConcurrentSetDemo {
	@Test
	public void t1() {
		ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(4);
		set.add(new Integer(4));
		set.remove(4);
	
		System.out.println(set);
	}
}
