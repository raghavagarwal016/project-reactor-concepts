package com.reactive.programming.operators;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class Delay {

  public static void main(String[] args) throws InterruptedException {

    Flux
        .range(1, 10)
        .log()
        .delayElements(Duration.ofSeconds(1)) // will be scheduled in a separate thread pool
        .subscribe(System.out::println);

    Thread.currentThread().join();

  }

}
