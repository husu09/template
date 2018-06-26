package com.su.concurrency;
/**
 * 多线程执行同一方法测试
 * */

import java.util.concurrent.atomic.AtomicBoolean;

public class MethodsExecutor {
	
	private AtomicBoolean atomicBoolean = new AtomicBoolean();
	
	private void add() {
		boolean isFlag = atomicBoolean.compareAndSet(false, true);
		while (!isFlag) {
			add();
		}
		
	}
	
	
	private void del() {
		boolean isFlag = atomicBoolean.compareAndSet(false, true);
		while (!isFlag) {
			del();
		}
	}
	
}
