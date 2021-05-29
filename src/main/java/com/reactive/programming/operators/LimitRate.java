package com.reactive.programming.operators;

import reactor.core.publisher.Flux;

public class LimitRate {
  public static void main(String[] args) {

    Flux
        .range(1, 100)
        .log()
        .limitRate(10) // request 10 at a time default is unbound
        .subscribe(System.out::println);

    Flux
        .range(1, 100)
        .log()
        .limitRate(100, 90)
        .subscribe(System.out::println);

    Flux
        .range(1, 100)
        .log()
        .limitRate(10, 0)
        .subscribe(System.out::println);

  }
}
