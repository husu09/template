package com.su;

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
}
