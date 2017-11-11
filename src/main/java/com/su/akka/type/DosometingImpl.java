package com.su.akka.type;

import akka.actor.ActorContext;
import akka.actor.TypedActor;
import akka.actor.TypedActorFactory;

public class DosometingImpl implements Dosomting {
	public int i = 0;

	@Override
	public void doSometing() {
		i++;
	}

	@Override
	public void print() {
		System.out.println(i);
		ActorContext context = TypedActor.context();
		System.out.println(context);
		Dosomting dst = TypedActor.<Dosomting>self();
		System.out.println(dst);
		TypedActorFactory typedActorFactory = TypedActor.get(TypedActor.context());
		System.out.println(typedActorFactory);
	}
	

}
