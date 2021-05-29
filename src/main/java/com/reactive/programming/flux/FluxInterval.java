package com.reactive.programming.flux;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class FluxInterval {
  public static void main(String[] args) throws InterruptedException {
    Flux
        .interval(Duration.ofSeconds(1))
        .subscribe(System.out::println);

    Thread.currentThread().join();;
  }
}
