/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.su.netty.proto;

import com.su.proto.DemoProto;
import com.su.proto.DemoProto.SearchRequest;
import com.su.proto.DemoProto.SearchResponse;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
@Sharable
public class SubReqServerHandler extends SimpleChannelInboundHandler<DemoProto.SearchRequest> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, SearchRequest msg) throws Exception {
		
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		SearchResponse build = DemoProto.SearchResponse.newBuilder().setResult("result").build();
		ctx.writeAndFlush(build);
	}

	
}
