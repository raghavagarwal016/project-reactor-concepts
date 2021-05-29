package com.reactive.programming.mono;

import com.github.javafaker.Faker;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

//default the mono is executed using main thread
public class NonBlockingMono {

  public static void main(String[] args) {
    getName();

    getName()
        .subscribeOn(Schedulers.boundedElastic()) // used to do async execution
        .subscribe(System.out::println);

    getName();
  }

  private static Mono<String> getName() {
    System.out.println("Inside getName method");
    return Mono.fromSupplier(() -> {
      System.out.println("Generating name");
      sleep();
      return Faker.instance().name().fullName();
    }).map(String::toUpperCase);
  }

  private static void sleep() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
