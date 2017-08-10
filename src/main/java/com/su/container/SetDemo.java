package com.su.container;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * @author husu
 * Set 接口是 Collection 的子接口
 * Set 中的元素不可重复
 * Set 中可以存储 null 值
 * 1. 当两个对象的 equals 和 hashCode 都相等时，Set 集合才会判断两个对象相同
 * 2. 当两个对象的 equals 不等, hashCode 相等时，Set 集合会在 hashCode 的位置以链式结构的形式存储多个对象
 * 3. 当两个对象的 equals 相同, hashCode 不等时，Set 集合会在不同的 hashCode 位置存储对象
 * 所以当重写 equals 方法时也应该重写  hashCode 方法，当两个对象的 equals 为 true 时，它们的 hashCode 也应该相同
 * 
 * HashSet 无序的，不允许元素重复的集合
 * LinkedHashSet 有序的，不允许元素重复的集合，遍历更快
 * TreeSet 排序的，不允许元素重复的集合
 */
public class SetDemo {
	static class Person {
		private int age;
		
		public Person(int age) {
			this.age = age;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + age;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Person other = (Person) obj;
			if (age != other.age)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Person [age=" + age + "]";
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
		
		
		
	}
	
	public static void main(String[] args) {
		Person p1 = new Person(10);
		Person p2 = new Person(20);
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		
		Set<Person> set = new HashSet<>();
		set.add(p1);
		set.add(p1);
		System.out.println(set.size()); // 1
	}
	
	/**
	 * 如果修改 HashSet 中的元素时，有可能导致集合中的元素相同，从而导致 HashSet 无法准确访问对象
	 */
	@Test
	public void updateSetItem() {
		Set<Person> set = new HashSet<>();
		set.add(new Person(10));
		set.add(new Person(20));
		set.add(new Person(30));
		set.add(new Person(40));
		// [Person [age=20], Person [age=40], Person [age=10], Person [age=30]]
		System.out.println(set);
		
		Person p = set.iterator().next();
		p.setAge(40);
		// 修改第一个元素的值 20 = 40，这个时候对象保存在 haseCode 为 20 的对象上，但值却是 40
		// [Person [age=40], Person [age=40], Person [age=10], Person [age=30]]
		System.out.println(set);
		
		set.remove(p);
		// [Person [age=40], Person [age=10], Person [age=30]]
		System.out.println(set);
		
		System.out.println(set.contains(new Person(40))); // false
		System.out.println(set.contains(new Person(20))); // false
	}
	
	/**
	 * 测试 set 集合中，equals 不等 hashcode 相同的情况
	 */
	@Test
	public void equalsHashCode() {
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
		HashSet<Person> set = new HashSet<>();
		set.add(new Person("tom"));
		set.add(new Person("tom"));
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			System.out.println(person);
		}
	}
	
	/**
	 * treeSet 只能存储同一种类型的对象，并且该对象必须实现 Compareable 接口，实现对象之间的比较
	 * 如两个对象的 compareTo 方法比较相等，新元素无法添加到时集合中 
	 * 当通过 compareTo 比较两个对象相等时，它们的 equals 方法也应该返回 true
	 * 如果修改 HashSet 中的元素时，有可能导致集合中的元素相同，排序错乱
	 */
	@Test
	public void treeSetDemo() {
		TreeSet<Object> set = new TreeSet<>(new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				return 1;
			}
		});
		//set.first();
		//set.last();
		set.add("a");
		set.add("a");
		set.add(1);
		set.add(1);
		
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object);
		}
	}
}
