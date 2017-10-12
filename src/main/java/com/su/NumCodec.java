package com.su;

import java.math.BigDecimal;

public class NumCodec {
	public static double encode(long v) {
		return v * 10000000000.0 + 10000000000.0 - 
				System.currentTimeMillis()/1000;
	}
	
	public static long decode(double v) {
		return (long)(v/10000000000.0);
	}
	
	public static void main(String[] args) throws InterruptedException {
		double v1 = encode(1000000);
		System.out.println(new BigDecimal(v1));
		Thread.sleep(1000);
		double v2 = encode(1000000);
		System.out.println(new BigDecimal(v2));
		System.out.println(v1 == v2);
	}
	
}
