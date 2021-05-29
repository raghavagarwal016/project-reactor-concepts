package com.reactive.programming.mono;

import com.github.javafaker.Faker;

import reactor.core.publisher.Mono;

public class MonoFromSupplier {
  public static void main(String[] args) {

    //use Mono.just if we already have data.
    Mono<String> nameMono = Mono.just(getName());

    Mono<String> nameMono2 = Mono.fromSupplier(() -> getName());

    nameMono2.subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));

  }

  //this method will be invoked when using Mono.just() but not with supplier
  private static String getName() {
    System.out.println("Generating name");
    return Faker.instance().name().fullName();
  }

}
