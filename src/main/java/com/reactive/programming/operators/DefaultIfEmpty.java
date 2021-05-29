package com.reactive.programming.operators;

import reactor.core.publisher.Flux;

public class DefaultIfEmpty {

  public static void main(String[] args) {
    getOrderNumbers()
        .filter(i -> i > 10)
        .defaultIfEmpty(-1) // if empty return this
        .subscribe(System.out::println);

    getOrderNumbers12()
        .filter(i -> i > 10)
        .defaultIfEmpty(-1) // if empty return this
        .subscribe(System.out::println);
  }

  private static Flux<Integer> getOrderNumbers() {
    return Flux
        .range(1, 10);
  }

  private static Flux<Integer> getOrderNumbers12() {
    return Flux
        .range(1, 12);
  }

}
