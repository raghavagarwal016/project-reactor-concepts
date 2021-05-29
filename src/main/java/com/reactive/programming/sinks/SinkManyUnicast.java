package com.reactive.programming.sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyUnicast {

  public static void main(String[] args) {

    // creates a flux
    Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
    Flux<Object> flux = sink.asFlux();

    flux.subscribe(System.out::println);

    // will not work as we are using unicast
    //flux.subscribe(System.out::println);

    for (int i = 0; i < 10; i++) {
      sink.tryEmitNext("Hi, how are you: " + i);
    }
    sink.tryEmitComplete();

  }

}
