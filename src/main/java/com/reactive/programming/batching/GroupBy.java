package com.reactive.programming.batching;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class GroupBy {

  public static void main(String[] args) throws InterruptedException {

    Flux
        .range(1, 30)
        .delayElements(Duration.ofSeconds(1))
        .groupBy(i -> i % 2 == 0)//group based on the key
        .subscribe(flux -> process(flux, flux.key()));

    Thread.currentThread().join();

  }

  private static void process(Flux<Integer> flux, boolean key) {
    flux.subscribe(i -> System.out.println("Key: " + key + " Item: " + i));
  }

}
