package com.su.akka.one;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class RobotActor extends AbstractActor {
	// 创建actor的方法
	static public Props props() {
		return Props.create(RobotActor.class, () -> new RobotActor());
	}

	// 消息
	static public class Action {
		private final String message;
		private final int time;

		public Action(String message, int time) {
			this.message = message;
			this.time = time;
		}

		public String getMessage() {
			return message;
		}

		public int getTime() {
			return time;
		}

	}

	static public class TurnOnLight extends Action {
		public TurnOnLight(int time) {
			super("Turn on the living room light", time);
		}
	}

	static public class BoilWater extends Action {
		public BoilWater(int time) {
			super("Burn a pot of water", time);
		}
	}
	// #消息

	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	// 接收消息的方法
	@Override
	public Receive createReceive() {
		return receiveBuilder().match(TurnOnLight.class, t -> {
			
			log.info("{} after {} hour", t.getMessage(), t.getTime());
		}).match(BoilWater.class, b -> {
			log.info("{} after {} hour", b.getMessage(), b.getTime());
		}).matchAny(o -> log.info("received unknown message")).build();
	}

}
