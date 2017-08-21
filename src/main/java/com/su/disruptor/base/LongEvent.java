package com.su.disruptor.base;

/**
 * @author husu 事件对象
 */
public class LongEvent {
	private long value;

	public void set(long value) {
		this.value = value;
	}
	public long get() {
		return value;
	}
	
}
