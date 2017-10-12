package com.su.akka.sample;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
/**
 * 演示 actor 层级关系
 * */
class PrintMyActorRefActor extends AbstractActor {
  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .matchEquals("printit", p -> {
          ActorRef secondRef = getContext().actorOf(Props.empty(), "second-actor");
          System.out.println("Second: " + secondRef);
        })
        .build();
  }
}
public class ActorHierarchyExperiments {
  public static void main(String[] args) throws java.io.IOException {
    ActorSystem system = ActorSystem.create("test");

    ActorRef firstRef = system.actorOf(Props.create(PrintMyActorRefActor.class), "first-actor");
    System.out.println("First: " + firstRef);
    firstRef.tell("printit", ActorRef.noSender());

    System.out.println(">>> Press ENTER to exit <<<");
    try {
      System.in.read();
    } finally {
      system.terminate();
    }
  }
}