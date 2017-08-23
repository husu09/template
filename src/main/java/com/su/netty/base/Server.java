package com.su.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Server {
	public static void main(String[] args) throws InterruptedException {
		// boss线程负责监听端口
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// worker线程负责数据读写
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		ServerBootstrap b = new ServerBootstrap();
		try {
			
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			
			b.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new StringEncoder());
					p.addLast(new StringDecoder());
					p.addLast(new ServerHandler());
				}
			});
			ChannelFuture f = b.bind(8080).sync();
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
