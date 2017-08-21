package com.su.disruptor.base;

import com.lmax.disruptor.EventFactory;

/**
 * @author husu 事件工厂
 */
public class LongEventFactory implements EventFactory<LongEvent> {
	public LongEvent newInstance() {
		return new LongEvent();
	}
}
