package com.su.log;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author husu
 * 使用 slf4j 示例
 * slf4j 是一个日志接口，可以在不同的实现中切换
 */
public class LogTest {
	private final Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				TimeUnit.SECONDS.sleep(2);
				logger.info("日志测试" + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		LogTest logTest = new LogTest();
		logTest.run();
	}
}
