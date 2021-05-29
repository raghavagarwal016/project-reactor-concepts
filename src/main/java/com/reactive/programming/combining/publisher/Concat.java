package com.reactive.programming.combining.publisher;

import reactor.core.publisher.Flux;

//only works when complete data is emitted
public class Concat {
  public static void main(String[] args) {

    Flux<String> publisher1 = Flux.just("a", "b");
    Flux<String> publisher2 = Flux.error(new RuntimeException());
    Flux<String> publisher3 = Flux.just("c", "d", "e");


    Flux<String> publisher4 = Flux.concat(publisher1, publisher2, publisher3);
    publisher4.subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));

    Flux<String> publisher5 = Flux.concatDelayError(publisher1, publisher2, publisher3);
    publisher5.subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));

  }

}
