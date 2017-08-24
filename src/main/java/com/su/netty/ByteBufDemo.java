package com.su.netty;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * readerIndex ： 读取指针 writerIndex ： 写入指针 capacity : 缓冲区容量
 */
public class ByteBufDemo {
	public static void main(String[] args) {
		ByteBuf b = Unpooled.buffer();
		b.writeInt(100);
		int i = b.readInt();
		System.out.println(b.readerIndex());
		System.out.println(i);
	}

	@Test
	public void t1() {
		ByteBuf b = Unpooled.buffer();
		// 一个int占4个byte数组位
		b.writeInt(10000);
		byte[] array = b.array();
		for (int i = 0; i < 32; i++) {
			System.out.print(array[i]);
		}
	}

	@Test
	public void t2() {
		ByteBuf b = Unpooled.buffer(1);
		// 一个int占4个byte数组位
		b.writeInt(10000);
		byte[] array = b.array();
		for (int i = 0; i < 4; i++) {
			System.out.print(array[i]);
		}
	}

	@Test
	public void t3() {
		ByteBuf b = Unpooled.buffer(1);
		// 一个int占4个byte数组位
		b.writeByte(400000000);
		byte[] array = b.array();
		System.out.println(array.length);
		for (int i = 0; i < 32; i++) {
			System.out.print(array[i]);
		}
	}

	/**
	 * int转换成byte数组
	 */
	@Test
	public void t4() {
		int i = 10000;
		byte[] result = new byte[4];
		// 由高位到低位
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		for (int j = 0; j < 4; j++) {
			System.out.print(result[j]);
		}
	}

	/**
	 * byte数组转换成int
	 */
	@Test
	public void t5() {
		byte[] bytes = new byte[] { 0, 0, 39, 16 };
		int value = 0;
		// 由高位到低位
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		System.out.println(value);
	}
}
