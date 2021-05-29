package com.reactive.programming.threading;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

//publishOn is used to switch to different thread pool
public class PublishOn {

  public static void main(String[] args) {

    Flux<Integer> flux = Flux
                  .create(fluxSink -> {
                    printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                      fluxSink.next(i);
                    }
                    fluxSink.complete();
                  });

    flux
        .publishOn(Schedulers.boundedElastic())
        .subscribe(i -> printThreadName("subscribe"));

  }

  private static void printThreadName(String message) {
    System.out.println(message + "\t\t" + "Thread: " + Thread.currentThread().getName());
  }

}
