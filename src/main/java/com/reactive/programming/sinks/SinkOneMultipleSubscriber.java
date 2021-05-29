package com.reactive.programming.sinks;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkOneMultipleSubscriber {

  public static void main(String[] args) {

    Sinks.One<Object> sink = Sinks.one();
    Mono<Object> mono = sink.asMono();

    //two subscriber
    mono.subscribe(System.out::println);
    mono.subscribe(System.out::println);

    sink.emitValue("Hi", ((signalType, emitResult) -> {
      System.out.println(signalType.name());
      System.out.println(emitResult.name());
      return false;
    }));

  }

}
