package com.reactive.programming.sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkOne {
  public static void main(String[] args) {

    //gives a mono
    Sinks.One<Object> sink = Sinks.one();
    Mono<Object> mono = sink.asMono();

    mono.subscribe(System.out::println);

    sink.tryEmitValue("Hi");

  }
}
