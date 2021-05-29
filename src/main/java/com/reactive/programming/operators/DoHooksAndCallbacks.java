package com.reactive.programming.operators;

import reactor.core.publisher.Flux;

public class DoHooksAndCallbacks {
  public static void main(String[] args) {

    Flux
        .create(fluxSink -> {
          System.out.println("--Started");
          for (int i = 0; i < 10; i++) {
            fluxSink.next(i);
          }
          fluxSink.complete();
          System.out.println("--Completed");
        })
        .doOnComplete(() -> System.out.println("--DoOnComplete")) // have lot of do hooks
        .doFirst(() -> System.out.println("--DoFirst"))
        .doOnNext(i -> System.out.println("--" + i))
        .doOnSubscribe(subscription -> System.out.println("--" + subscription))
        .doFinally(signalType -> System.out.println(signalType))
        .subscribe(System.out::println);


  }
}
