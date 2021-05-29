package com.reactive.programming.sinks;

import java.time.Duration;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyDirectAll {
  public static void main(String[] args) {

    System.setProperty("reactor.bufferSize.small", "16");

    Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();
    Flux<Object> flux = sink.asFlux();

    flux.subscribe(new DefaultSubscriber("1st Subscriber"));
    flux.delayElements(Duration.ofMillis(200)).subscribe(new DefaultSubscriber("2nd Subscriber"));

    for (int  i = 0; i < 100; i++) {
      sink.tryEmitNext(i);
    }
    sink.tryEmitComplete();
  }
}
