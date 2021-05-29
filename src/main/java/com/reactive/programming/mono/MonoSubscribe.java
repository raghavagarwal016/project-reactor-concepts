package com.reactive.programming.mono;

import reactor.core.publisher.Mono;

public class MonoSubscribe {
  public static void main(String[] args) {

    //create publisher
    Mono nameMono = Mono.just("Raghav Agarwal");

    // will not do anything but will emit the name to subscriber
    System.out.println(nameMono.subscribe());

    // will print the name and Process Completed when there is nothing left to emit.
    nameMono.subscribe(name -> System.out.println(name), err -> System.out.println(err), () -> System.out.println("Process Completed"));

    //create publisher that will throw error
    Mono<Integer> lengthMono = Mono.just("Raghav Agarwal").map(String::length).map(l -> l / 0);

    // will not do anything but will emit the name to subscriber
    System.out.println(lengthMono.subscribe());

    // will print error. onComplete is not executed in case of a error
    lengthMono.subscribe(length -> System.out.println(length), err -> System.out.println(err), () -> System.out.println("Process Completed"));


  }
}
