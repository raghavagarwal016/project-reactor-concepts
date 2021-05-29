package com.reactive.programming.threading;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOn {

  public static void main(String[] args) throws InterruptedException {

    Flux
        .create(fluxSink -> {
          printThreadName("create");
          fluxSink.next(1);
         })
        .subscribeOn(Schedulers.boundedElastic())
        .doOnNext(i -> printThreadName("doNext"))
        .subscribe(i -> printThreadName("subscriber: " + i));


    Thread.currentThread().join();

  }

  private static void printThreadName(String message) {
    System.out.println(message + "\t\t" + "Thread: " + Thread.currentThread().getName());
  }

}
