package com.su.netty;

import java.nio.ByteBuffer;

import org.junit.Test;
public class ByteBufferDemo {
	public static void main(String[] args) {
		// 创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(100);
		/**
		 * 创建时 position = 0 , limt = capactiy = 缓冲区长度
		 * +-----------------------+
		 * |                       |
		 * +-----------------------+
		 * position              limit
		 *                       capacity
		 * */
		// 写入数据
		String s = "Netty 权威指南";
		buffer.put(s.getBytes());
		/**
		 * 写入数据后  position = 写入数据长度 , limt = capactiy = 缓冲区长度
		 * +-----------------------+
		 * |Netty 权威指南 |          |
		 * +-----------------------+
		 *           position    limit
		 *                       capacity
		 * */
		// 准备读取数据
		buffer.flip();
		/**
		 * flip 后  position = 0 , limt = 写入数据长度, capacity = 缓冲区长度
		 * +-----------------------+
		 * |Netty 权威指南 |          |
		 * +-----------------------+
		 * position   limit     capacity
		 * 
		 * */
		// 读取数据 buffer.remaining() = 从 position 至 limit 的长度，可读数据长度
		byte[] arr = new byte[buffer.remaining()];
		buffer.get(arr);
		/**
		 * get 后  position = 已读取长度 , limt = capactiy = 缓冲区长度
		 * +-----------------------+
		 * |Netty 权威指南 |          |
		 * +-----------------------+
		 *           limit     capacity
		 *           position
		 * */
		System.out.println(new String(arr));
		// 还原标识，数据不会清除
		buffer.clear();
		/**
		 * 创建时 position = 0 , limt = capactiy = 缓冲区长度
		 * +-----------------------+
		 * |Netty 权威指南                                   |
		 * +-----------------------+
		 * position              limit
		 *                       capacity
		 * */
		System.out.println(buffer.remaining());
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		
	}
	
	/**
	 * ByteBuffer.compact() 用法
	 * */
	@Test
	public void compact() {
		ByteBuffer buffer = ByteBuffer.allocate(100);
		String s = "String";
		buffer.put(s.getBytes());
		// while (buffer.hasRemaining()) 不能在 封装前判断是否有可读数据，这时的位置是不对的
		while (true) {
			buffer.flip();
			// 封装后判断是否有可读数据
			if (!buffer.hasRemaining()) {
				break;
			}
			byte[] arr = new byte[1];
			buffer.get(arr);
			System.out.println(new String(arr));
			/**
			 * 1.第一次循环
			 * 
			 * 封装前数据
			 * +-----------------------+
			 * |String                 |
			 * +-----------------------+
			 *    position = 6      capacity = limit
			 * 
			 * 封装数据
			 * +-----------------------+
			 * |String|                |
			 * +-----------------------+
			 * position = 0         capacity
			 * 		limit = 6
			 * 
			 * 读取数据后
			 * +-----------------------+
			 * |S|tring|               |
			 * +-----------------------+
			 * position = 1         capacity
			 * 		limit = 6
			 * 
			 * 压缩后，丢弃已读取数据，重置读取 position 位置和 limit 位置，回复到 flip 之前的状态
			 * +-----------------------+
			 * |tring|                 |
			 * +-----------------------+
			 * position = 5         capacity = limit
			 * 
			 * */
			buffer.compact();
		}
		
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		
	}
	
	
	/**
	 * 遍历 Bytebuff 数据
	 * */
	@Test
	public void foreach() {
		ByteBuffer buffer = ByteBuffer.allocate(100);
		String s = "String";
		buffer.put(s.getBytes());
		buffer.flip();
		/**
		 * flip 后  position = 0 , limt = 写入数据长度, capacity = 缓冲区长度
		 * +-----------------------+
		 * |String|                |
		 * +-----------------------+
		 * position   limit     capacity
		 * 
		 * */
		byte[] arr = new byte[1];
		
		while (buffer.hasRemaining()) {
			buffer.get(arr);
			/**
			 * 1.第一次循环  position = 1 , limt = 写入数据长度, capacity = 缓冲区长度
			 * +-----------------------+
			 * |S|tring|               |
			 * +-----------------------+
			 * position   limit     capacity
			 * 
			 * 2.最后一次循环  position = limt , limt = 写入数据长度, capacity = 缓冲区长度
			 * +-----------------------+
			 * |String||               |
			 * +-----------------------+
			 * position   limit     capacity
			 * 
			 * */
			System.out.println(new String(arr));
			
		}
		
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		
	}
}
