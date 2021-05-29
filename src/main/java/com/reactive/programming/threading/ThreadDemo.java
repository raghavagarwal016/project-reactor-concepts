package com.reactive.programming.threading;

import reactor.core.publisher.Flux;

public class ThreadDemo {

  public static void main(String[] args) throws InterruptedException {

  Runnable runnable = () ->  Flux
        .create(fluxSink -> {
          printThreadName("create");
          fluxSink.next(1);
        })
        .doOnNext(i -> printThreadName("doNext"))
        .subscribe(i -> printThreadName("subscriber: " + i));

  for (int i = 0; i < 2; i++) {
    new Thread(runnable).start();
  }

  Thread.currentThread().join();

  }

  private static void printThreadName(String message) {
    System.out.println(message + "\t\t" + "Thread: " + Thread.currentThread().getName());
  }

}
