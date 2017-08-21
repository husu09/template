package com.su.disruptor.base;

import com.lmax.disruptor.EventHandler;

/**
 * @author husu 消息者监听事件
 */
public class LongEventHandler implements EventHandler<LongEvent> {
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
		System.out.println(Thread.currentThread().getName() + " Event: " + event.get());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
