package com.su.netty.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.su.proto.DemoProto;
import com.su.proto.DemoProto.SearchRequest;

public class ProtoTest {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		SearchRequest searchRequest = DemoProto.SearchRequest.newBuilder()
			.setQuery("tom")
			.setPageNumber(1)
			.setResultPerPage(10).build();
		// protobuf 序列化成字节数组
		byte[] byteArray = searchRequest.toByteArray();
		
		// 字节数组反序列化成对象
		SearchRequest request = DemoProto.SearchRequest.parseFrom(byteArray);
		System.out.println(request);
		
	}
}
