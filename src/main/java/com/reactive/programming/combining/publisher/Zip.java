package com.reactive.programming.combining.publisher;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class Zip {

  public static void main(String[] args) throws InterruptedException {

    Flux.zip(getBody(), getEngine(), getTyre())
        .subscribe(System.out::println);
  }

  private static Flux<String> getBody() {
    return Flux.range(1, 5).map(i -> "Body " + i);
  }

  private static Flux<String> getEngine() {
    return Flux.range(1, 3).map(i -> "Engine " + i);
  }

  private static Flux<String> getTyre() {
    return Flux.range(1, 3).map(i -> "Typre " + i);
  }

}

