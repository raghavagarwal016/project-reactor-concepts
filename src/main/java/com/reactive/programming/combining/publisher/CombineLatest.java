package com.reactive.programming.combining.publisher;

import java.time.Duration;

import reactor.core.publisher.Flux;

//combine latest item from all publisher
public class CombineLatest {
  public static void main(String[] args) throws InterruptedException {

    Flux
        .combineLatest(getString(), getNumbers(), (string, number) -> string + number)
        .subscribe(System.out::println);

    Thread.currentThread().join();


  }

  private static Flux<String> getString() {
    return Flux.just("A", "B", "C", "D", "E")
        .delayElements(Duration.ofSeconds(1));
  }

  private static Flux<Integer> getNumbers() {
    return Flux.just(1, 2, 3)
        .delayElements(Duration.ofSeconds(3));
  }
}
