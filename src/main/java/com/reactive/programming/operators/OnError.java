package com.reactive.programming.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OnError {
  public static void main(String[] args) {

    Flux
        .range(1, 10)
        .log()
        .map(i -> 10 /  (3 - i))
        .onErrorReturn(-1)
        .subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));

    Flux
        .range(1, 10)
        .log()
        .map(i -> 10 /  (3 - i))
        .onErrorResume(e -> fallback())
        .subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));

    Flux
        .range(1, 10)
        .log()
        .map(i -> 10 /  (3 - i))
        .onErrorContinue((e, o) -> fallback()) //  when to continue in case of error
        .subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));

  }

  private static Mono<Integer> fallback() {
    return Mono.fromSupplier(() -> 3);
  }
}
