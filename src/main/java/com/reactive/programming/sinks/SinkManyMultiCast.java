package com.reactive.programming.sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyMultiCast {
  public static void main(String[] args) {

    // creates a flux
    Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
    Flux<Object> flux = sink.asFlux();

    flux.subscribe(System.out::println);

    // will  work as we are using multicast
    flux.subscribe(System.out::println);

    for (int i = 0; i < 10; i++) {
      sink.tryEmitNext("Hi, how are you: " + i);
    }
    sink.tryEmitComplete();

  }
}
