package com.su;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author husu
 * 验证临时代码
 *
 */
public class TempTest {
	public static void main(String[] args) {
		// 
		
	}
	
	/**
	 * 计算折扣百分比
	 * */
	@Test
	public void t1() {
		System.out.println(1-5/100f);
	}
	
	/**
	 * 测试List<Integer> 的 contains 方法
	 * 包装类型可以contains基础类型
	 * */
	@Test
	public void t2() {
		List<Integer> list = new ArrayList<>();
		list.add(new Integer(1));
		System.out.println(list.contains(new Integer(1)));
	}
	
	@Test
	public void t3() {
		int num = 2;
		System.out.println(num*=1.5);
	}
	
	/**
	 * 测试 list 是否可以添加空元素
	 * */
	@Test
	public void t4() {
		List<String> list = new ArrayList<>();
		list.add(null);
		System.out.println(list);
	}
	
	/**
	 * 输出时间戳和长度
	 * */
	@Test
	public void t5() {
		long time = System.currentTimeMillis();
		System.out.println(time);
		System.out.println(String.valueOf(time).length());
	}
	
	/**
	 * 测试负负得正
	 * */
	@Test
	public void t6() {
		int a = -5;
		System.out.println(-a);
	}
	
	@Test
	public void t7() {
		double d = 1234567890123456.0;
		d = 1234567890123456.0 * 10 + 7;
		System.out.println(new BigDecimal(d));
		System.out.println(new BigDecimal(1234567890123456788.0));
		System.out.println(new BigDecimal(1234567890123456789.0));
	}
	
	@Test
	public void t9() {
		
	}
	
}
