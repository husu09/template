package com.su.base;

/**
 * @author husu
 * 枚举
 */
public enum EnumDemo {
	ONE(1),
	TWO(2);
	
	private int id;
	
	EnumDemo(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}



	public static void main(String[] args) {
		System.out.println(EnumDemo.ONE);
		System.out.println(EnumDemo.TWO);
	}
}
