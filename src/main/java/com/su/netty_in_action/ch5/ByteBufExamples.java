package com.su.netty_in_action.ch5;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Random;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufExamples {
	public static void heapBuffer(ByteBuf heapBuf) {
		if (heapBuf.hasArray()) {
			byte[] array = heapBuf.array();
			int offset = heapBuf.arrayOffset() + heapBuf.readInt();
			int length = heapBuf.readableBytes();
			handleArray(array, offset, length);
		}
	}

	public static void dirctBuffer(ByteBuf dirctBuf) {
		if (!dirctBuf.hasArray()) {
			int length = dirctBuf.readableBytes();
			byte[] arr = new byte[length];
			dirctBuf.getBytes(dirctBuf.readerIndex(), arr);
			handleArray(arr, 0, length);
		}
	}

	public static void byteBuffer(ByteBuffer header, ByteBuffer body) {
		ByteBuffer[] message = { header, body };
		ByteBuffer message2 = ByteBuffer.allocate(header.remaining() + body.remaining());
		message2.put(header);
		message2.put(body);
		message2.flip();

	}

	public static void byteBufComposite(ByteBuf header, ByteBuf body) {
		CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
		messageBuf.addComponents(header, body);
		messageBuf.removeComponent(0);

		for (int i = 0; i < messageBuf.numComponents(); i++) {
			System.out.println(messageBuf.component(i).toString());
		}
	}

	public static void byteBufCompositeArray(CompositeByteBuf compBuf) {
		int length = compBuf.readableBytes();
		byte[] array = new byte[length];
		compBuf.getBytes(compBuf.readerIndex(), array);
		handleArray(array, 0, length);
	}

	public static void byteBufRelativeAccess(ByteBuf buffer) {
		for (int i = 0; i < buffer.capacity(); i++) {
			byte b = buffer.getByte(i);
			System.out.println((char) b);
		}
	}

	public static void readAllData(ByteBuf buffer) {
		while (buffer.isReadable()) {
			System.out.println(buffer.readByte());
		}
	}

	private static Random random = new Random();

	public static void write(ByteBuf buffer) {
		while (buffer.writableBytes() >= 4) {
			buffer.writeInt(random.nextInt());
		}
	}

	public static void byteBufProcessor(ByteBuf buffer) {
		int index = buffer.forEachByte(ByteBufProcessor.FIND_CR);
	}

	/**
	 * Listing 5.10
	 */
	public static void byteBufSlice() {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8); // 1

		ByteBuf sliced = buf.slice(0, 14); // 2
		System.out.println(sliced.toString(utf8)); // 3

		buf.setByte(0, (byte) 'J'); // 4
		assert buf.getByte(0) == sliced.getByte(0);
	}

	/**
	 * Listing 5.11
	 */
	public static void byteBufCopy() {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8); // 1

		ByteBuf copy = buf.copy(0, 14); // 2
		System.out.println(copy.toString(utf8)); // 3

		buf.setByte(0, (byte) 'J'); // 4
		assert buf.getByte(0) != copy.getByte(0);
	}
	
	public static void byteBufSetGet() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);	//1
        System.out.println((char)buf.getByte(0));					//2

        int readerIndex = buf.readerIndex();						//3
        int writerIndex = buf.writerIndex();

        buf.setByte(0, (byte)'B');							//4

        System.out.println((char)buf.getByte(0));					//5
        assert readerIndex == buf.readerIndex();					//6
        assert writerIndex ==  buf.writerIndex();
    }
	
	/**
     * Listing 5.13
     */
    public static void byteBufWriteRead() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);	//1
        System.out.println((char)buf.readByte());					//2

        int readerIndex = buf.readerIndex();						//3
        int writerIndex = buf.writerIndex();						//4

        buf.writeByte((byte)'?');							//5

        assert readerIndex == buf.readerIndex();
        assert writerIndex != buf.writerIndex();
    }
    
	private static void handleArray(byte[] array, int offset, int len) {

	}


}
