package com.reactive.programming.sinks;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkEmitFailure {
  public static void main(String[] args) {

    //gives a mono
    Sinks.One<Object> sink = Sinks.one();
    Mono<Object> mono = sink.asMono();

    mono.subscribe(System.out::println);

    sink.emitValue("Hi", ((signalType, emitResult) -> {
      System.out.println(signalType.name());
      System.out.println(emitResult.name());
      return false;
    }));

    sink.emitValue("Hello", ((signalType, emitResult) -> {
      System.out.println(signalType.name());
      System.out.println(emitResult.name());
      return false;
    }));

  }
}
