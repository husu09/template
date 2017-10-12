package com.su.akka.two;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ComputeActor extends AbstractActor {
	// 属性
	private static long sum;

	// 创建actor的方法
	static public Props props() {
		return Props.create(ComputeActor.class, () -> new ComputeActor());
	}

	// 消息定义
	static public class Increment {
		public final int value;

		public Increment(int i) {
			this.value = i;
		}
	}

	static public class Print {

	}
	// 消息定义结束

	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	// 接收消息的方法
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Increment.class, i -> sum += i.value)
				.match(Print.class, p -> log.info("sum = " + sum))
				.matchAny(o -> log.info("received unknown message"))
				.build();
	}

}
