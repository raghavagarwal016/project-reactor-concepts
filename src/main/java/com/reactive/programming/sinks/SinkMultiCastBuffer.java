package com.reactive.programming.sinks;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkMultiCastBuffer {
  public static void main(String[] args) {

    // creates a flux
    Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
    Flux<Object> flux = sink.asFlux();

    sink.tryEmitNext("Hi");
    sink.tryEmitNext("How are you");

    flux.subscribe(new DefaultSubscriber("1st Subscriber"));
    flux.subscribe(new DefaultSubscriber("2nd Subscriber"));
    sink.tryEmitNext("?");

    sink.tryEmitComplete();

  }
}
