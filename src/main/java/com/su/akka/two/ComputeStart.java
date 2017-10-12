package com.su.akka.two;

import com.su.akka.two.ComputeActor.Increment;
import com.su.akka.two.ComputeActor.Print;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;;

/**
 * 验证 akka actor 在多线程下的安全性
 * */
public class ComputeStart {
	
	public static void main(String[] args) throws InterruptedException {
		t2();
	}
	
	/**
	 * 多个线程并发向 actor 发送消息操作 actor 中的状态是安全的，因为 actor 是串行化执行所有操作的
	 * */
	public static void t1() throws InterruptedException {
		final ActorSystem system = ActorSystem.create("root-system");
		final ActorRef computeActor = system.actorOf(ComputeActor.props(), "computeActor");
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i <10000000; i++) {
					computeActor.tell(new Increment(1), ActorRef.noSender());
					computeActor.tell(new Print(), ActorRef.noSender());
				}
				
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		computeActor.tell(new Print(), ActorRef.noSender());
		computeActor.tell("", ActorRef.noSender());
	}
	
	/**
	 * 多个 actor 操作同一数据是不安全的，因 每个 actor 虽然是串行安全的，但 actor 和 actor 之间是异步的
	 * */
	public static void t2() throws InterruptedException {
		final ActorSystem system = ActorSystem.create("root-system");
		final ActorRef computeActor1 = system.actorOf(ComputeActor.props(), "computeActor1");
		final ActorRef computeActor2 = system.actorOf(ComputeActor.props(), "computeActor2");
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <10000000; i++) {
					computeActor1.tell(new Increment(1), ActorRef.noSender());
				}
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <10000000; i++) {
					computeActor2.tell(new Increment(1), ActorRef.noSender());
				}
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		computeActor1.tell(new Print(), ActorRef.noSender());
		//system.terminate();
	}
}
