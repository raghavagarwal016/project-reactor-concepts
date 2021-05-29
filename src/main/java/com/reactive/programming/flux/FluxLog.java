package com.reactive.programming.flux;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class FluxLog {

  public static void main(String[] args) {
    Flux.range(1, 10)
        .log()
        .map( i -> Faker.instance().name().fullName())
        .subscribe(System.out::println);
  }

}
