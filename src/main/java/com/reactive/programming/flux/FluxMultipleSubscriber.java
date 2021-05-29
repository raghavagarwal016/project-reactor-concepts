package com.reactive.programming.flux;

import reactor.core.publisher.Flux;

public class FluxMultipleSubscriber {

  public static void main(String[] args) {

   Flux<Integer> integerFlux =  Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

   integerFlux.subscribe(i -> System.out.println("Subscriber 1 :" + (i + 1)));
   integerFlux.subscribe(i -> System.out.println("Subscriber 2 :" + (i - 1)));

  }

}
