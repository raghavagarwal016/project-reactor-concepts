package com.reactive.programming.mono;

import reactor.core.publisher.Mono;

// easiest way to create Mono is Mono.just()
public class MonoJust {
  public static void main(String[] args) {
    //publisher
    Mono<Integer> integerMono = Mono.just(1);

    // will just call toString method as the mono is not subscribed
    System.out.println(integerMono);

    // will subscribe the mono to print the data
    integerMono.subscribe(i -> System.out.println(i));
  }
}
