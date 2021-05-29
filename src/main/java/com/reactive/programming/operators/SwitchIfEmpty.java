package com.reactive.programming.operators;

import reactor.core.publisher.Flux;

public class SwitchIfEmpty {

  public static void main(String[] args) {

    getOrderNumbers()
        .filter(i -> i > 10)
        .switchIfEmpty(getOrderNumbers12()) //fallback if the empty
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
