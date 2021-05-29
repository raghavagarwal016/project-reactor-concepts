package com.reactive.programming.operators;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class Timeout {

  public static void main(String[] args) throws InterruptedException {

    getOrderNumbers()
        .timeout(Duration.ofSeconds(2))
        .subscribe(System.out::println, System.out::println, () -> System.out.print("Process Completed"));

    getOrderNumbers()
        .timeout(Duration.ofSeconds(2), getOrderNumbersFallback()) //provides fallback in case of timeout
        .subscribe(System.out::println, System.out::println, () -> System.out.print("Process Completed"));

    Thread.currentThread().join();

  }

  private static Flux<Integer> getOrderNumbers() {
    return Flux
        .range(1, 10)
        .delayElements(Duration.ofSeconds(5));
  }

  private static Flux<Integer> getOrderNumbersFallback() {
    return Flux
        .range(1, 10)
        .delayElements(Duration.ofSeconds(1));
  }

}
