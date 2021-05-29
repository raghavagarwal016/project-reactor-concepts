package com.reactive.programming.flux;

import reactor.core.publisher.Flux;

public class FluxToMono {
  public static void main(String[] args) {

    Flux.range(1, 10)
        .next()
        .subscribe(System.out::println, System.out::println, () -> System.out.print("Process Completed"));

  }
}
