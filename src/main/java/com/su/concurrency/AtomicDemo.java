package com.su.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

import org.junit.Test;

/**
 * @author husu
 * 基于 CAS 操作的原子类
 */
public class AtomicDemo {
	/**
	 * AtomicInteger 适合于多线程操作的整型对象
	 */
	@Test
	public void atomicInteger() {
		AtomicInteger i = new AtomicInteger();
	}
	/**
	 * 赋值操作不是线程安全的
	 * AtomicReference 适合于并发的情况下，修改对象引用
	 */
	@Test
	public void atomicReference() {
		AtomicReference<String> s = new AtomicReference<String>();
	}
	/**
	 * AtomicStampedReference 用于解决并发下的 ABA 问题
	 */
	@Test
	public void AtomicStampedReference() {
		AtomicStampedReference<Integer> i = new AtomicStampedReference<Integer>(0, 0);
	}
	/**
	 * AtomicIntegerFieldUpdater 包装基础类型数据为高并发类型
	 */
	@Test
	public void atomicIntegerFieldUpdater() {
		class Person {
			int age = 0;
		}
		AtomicIntegerFieldUpdater<Person> i = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
		
	}
	/**
	 * AtomicIntegerArray 并发的无锁数组
	 */
	@Test
	public void atomicStampedReference() {
		AtomicIntegerArray array = new AtomicIntegerArray(10);
	}
}
