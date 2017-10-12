package com.su.akka.one;

import com.su.akka.one.RobotActor.BoilWater;
import com.su.akka.one.RobotActor.TurnOnLight;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;;

/**
 * 基础的 akka 程序
 * */
public class RobotStart {
	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("robot-system");
		System.out.println("the ActorSystem logLevel is " + system.settings().LogLevel());
		// 创建actor
		final ActorRef robotActor  = system.actorOf(RobotActor.props(), "robotActor");
		// 发送消息
		robotActor.tell(new TurnOnLight(1), ActorRef.noSender());
		robotActor.tell(new BoilWater(2), ActorRef.noSender());
		robotActor.tell("", ActorRef.noSender());
	}
}
