package com.su.netty_in_action.ch4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class PlainNioServer {
	public void server(int port) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// 设置serverChannel为非阻塞
		serverChannel.configureBlocking(false);
		ServerSocket ssocket = serverChannel.socket();
		// 绑定端口
		ssocket.bind(new InetSocketAddress(port));
		// 打开Selector选择器
		Selector selector = Selector.open();
		// 把Serversocket 注册到时选择器
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
		for (;;) {
			// 阻塞直到有新的事件
			selector.select();
			// 获取所有事件
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			for (Iterator<SelectionKey> it = readyKeys.iterator(); it.hasNext();) {
				SelectionKey key = it.next();
				it.remove();
				if (key.isAcceptable()) {
					// 接受连接
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel client = server.accept();
					client.configureBlocking(false);
					client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
					System.out.println("Accepted connection from " + client);
				}
				if (key.isWritable()) {// key.isWritable() 一直为true，因为可以一直向客户端写数据
					// 向客户端写数据
					SocketChannel client = (SocketChannel) key.channel();
					ByteBuffer buffer = (ByteBuffer) key.attachment();
					while (buffer.hasRemaining()) {
						if (client.write(buffer) == 0) {
							break;
						}
						client.close();
					}
				}
			}
		}
	}
}
