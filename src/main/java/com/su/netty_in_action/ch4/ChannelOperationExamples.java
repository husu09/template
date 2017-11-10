package com.su.netty_in_action.ch4;

import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.CharsetUtil;

public class ChannelOperationExamples {
	
	/**
	 * channel 可以在任何引用的地方使用
	 * */
	public static void writingToChannel() {
		Channel channel = null;
		ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
		ChannelFuture cf = channel.writeAndFlush(buf);
		cf.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("Write successful");
				} else {
					System.err.println("Write error");
					future.cause().printStackTrace();
				}
				
			}
		});
	}
	
	/**
	 * channel 是线程安全的，可以多个线程调用
	 * */
	public static void writingToChannelManyThreads() {
		Channel channel = null;
		ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8).retain();
		Runnable writer = new Runnable() {
			
			@Override
			public void run() {
				channel.writeAndFlush(buf.duplicate());
			}
		};
		Executor executor = Executors.newCachedThreadPool();
		
		executor.execute(writer);
		
		executor.execute(writer);
	}
}
