package com.su.netty.base;

import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new StringEncoder());
					p.addLast(new StringDecoder());
					p.addLast(new ClientHandler());
				}
			});
			ChannelFuture f = b.connect("127.0.0.1", 8080).sync();
			Scanner sc = new Scanner(System.in);
			while (true) {
				String s = sc.next();
				f.channel().writeAndFlush(s);
			}
			//f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}
}
