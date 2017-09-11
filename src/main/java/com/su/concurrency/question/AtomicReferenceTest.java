package com.su.concurrency.question;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import org.junit.Test;

public class AtomicReferenceTest {
	/**
	 * 测试字符串赋值的原子性操作
	 * */
	public static void main(String[] args) {
		AtomicReference<String> s = new AtomicReference<>();
		System.out.println(s.compareAndSet(null, "b"));
		System.out.println(s.compareAndSet(null, "b"));
	}
	
	/**
	 * AtomicReferenceFieldUpdater 测试，将普通字符串包装成原子性的操作
	 * */
	@Test
	public void t1() {
		
		AtomicReferenceFieldUpdater<Person, String> at = AtomicReferenceFieldUpdater.newUpdater(Person.class, String.class, "name");
		Person p = new Person();
		System.out.println(at.compareAndSet(p, null, "a"));
		System.out.println(at.compareAndSet(p, null, "b"));
	}
	
	static class Person {
		volatile String name;
	}
}
