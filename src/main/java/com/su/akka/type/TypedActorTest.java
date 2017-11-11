package com.su.akka.type;

import org.junit.Test;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedActorExtension;
import akka.actor.TypedActorFactory;
import akka.actor.TypedProps;

public class TypedActorTest {
	public static void main(String[] args) throws Exception {
		ActorSystem system = ActorSystem.create("helloakka");
		Dosomting dst = TypedActor.get(system)
				.typedActorOf(new TypedProps<DosometingImpl>(Dosomting.class, DosometingImpl.class));
		
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000000; i++) {
					dst.doSometing();
				}
			}
		};
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		Thread t4 = new Thread(r);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		dst.print();
		
	
		
	}
	
	@Test
	public void t1() {
		ActorSystem system = ActorSystem.create("game");
		// 创建 TypeActor
		Dosomting dst =
				  TypedActor.get(system).typedActorOf(
				    new TypedProps<DosometingImpl>(Dosomting.class, DosometingImpl.class));
		
		TypedActorExtension extension =
		        TypedActor.get(system);
		// 判断一个对象是否是 Typed Actor 代理对象
		System.out.println(extension.isTypedActor(dst));
		
		// 返回一个actorRef
		ActorRef actorRef = extension.getActorRefFor(dst);
		System.out.println(actorRef);
		
		dst.print();
		
		
		// 返回一个 actorContext，在代理实现中调用
		// ActorContext context = TypedActor.context();
		
		// 返回 Typed Actor 代理对象，在代理实现中调用
		// Dosomting sq = TypedActor.<Dosomting>self();
		
		// 返回一个TypeActor工厂，在代理实现中调用
		// TypedActorFactory typedActorFactory = TypedActor.get(TypedActor.context());
		
		// 关闭actor
		TypedActor.get(system).stop(dst);
		// 所有调用完成后关闭
		TypedActor.get(system).poisonPill(dst);
		
	}
}
