package com.reactive.programming.batching;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// just like buffer but instead of giving list it gives flux
public class Window {

  private static AtomicInteger atomicInteger = new AtomicInteger();

  public static void main(String[] args) throws InterruptedException {

    eventStream()
        .window(0) // will collect 10 times and then publish flux of 10 items
        .flatMap(flux -> saveEvents(flux))
        .subscribe(new DefaultSubscriber("Buffer Batching"));

    Thread.currentThread().join();

  }

  private static Mono<Integer> saveEvents(Flux<String> flux) {
    return flux
        .doOnNext(System.out::println)
        .doOnComplete(() -> System.out.println("Saved"))
        .then(Mono.just(atomicInteger.getAndIncrement()));
  }

  private static Flux<String> eventStream() {
    return Flux
        .interval(Duration.ofMillis(300))
        .map(i -> "event: " + i);
  }

}
