package com.reactive.programming.retry;

import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;

public class Repeat {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);

  public static void main(String[] args) {

    getIntegers()
        .repeat(2) //will connect 2 more times. so total 3 times
        .subscribe(System.out::println);

  }

  private static Flux<Integer> getIntegers() {
    return Flux
            .range(1, 3)
            .doOnSubscribe(subscription -> System.out.println("Subscribed"))
            .doOnComplete(() -> System.out.println("Completed"))
            .map(i -> atomicInteger.getAndIncrement());

  }

}
