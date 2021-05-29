package com.reactive.programming.operators;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class Handle {
  public static void main(String[] args) {

    Flux
        .range(1, 100)
        .handle((integer, synchronousSink) -> { // requires a object and syncronizeSink
          synchronousSink.next(integer);
        })
        .subscribe(System.out::println);

    Flux
        .range(1, 100)
        .handle((integer, synchronousSink) -> {
          if (integer % 2 == 0) {
            synchronousSink.next(integer);
          }
        })
        .subscribe(System.out::println);

    Flux
        .generate(synchronousSink -> synchronousSink.next(Faker.instance().country().name()))
        .map(o -> (String)o)
        .handle((s, synchronousSink) -> {
          synchronousSink.next(s);
          if ("canada".equalsIgnoreCase(s)) {
            synchronousSink.complete();
          }
        })
        .subscribe(System.out::println);

  }
}
