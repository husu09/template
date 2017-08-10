package com.su.container;

import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Test;

/**
 * @author husu
 * HaspMap 无序的，key 和 value 可以 null 值
 * LinkedHashMap 有序的
 * TreeMap 排序的
 */
public class MapDemo {
	/**
	 * HashMap 在 key 的 hashcode 相同的情况下会在相同的位置以链表结构保存多条数据
	 * 取出时，会根据 key 的 hashcode 找到位置里的链表，再比较 equal 方法，取出相同的数据
	 */
	@Test
	public void equalsHashcode() {
		class Person {
			private String name;
			
			public Person(String name) {
				this.name = name;
			}
			
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((name == null) ? 0 : name.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				return false;
			}

			@Override
			public String toString() {
				return "Person [name=" + name + "]";
			}

			
		}
		
		HashMap<Person, String> map = new HashMap<>();
		Person p1 = new Person("tom");
		Person p2 = new Person("tom");
		System.out.println(p1.hashCode() + " :" + p2.hashCode());
		map.put(p1, "tom1");
		map.put(p2, "tom2");
		System.out.println(map.size());//2
		for (Entry<Person, String> e : map.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		System.out.println(map.get(p2));
	}
	
}
