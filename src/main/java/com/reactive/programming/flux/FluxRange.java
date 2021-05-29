package com.reactive.programming.flux;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class FluxRange {
  public static void main(String[] args) {
    Flux.range(2, 100)
        .filter( i -> i % 2 == 0)
        .subscribe(System.out::println);

    Flux.range(1, 10)
        .map( i -> Faker.instance().name().fullName())
        .subscribe(System.out::println);
  }
}
