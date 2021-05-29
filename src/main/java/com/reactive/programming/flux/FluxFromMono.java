package com.reactive.programming.flux;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxFromMono {

  public static void main(String[] args) {

   Mono<String> stringMono =  Mono.just(Faker.instance().name().fullName());
   Flux
       .from(stringMono)
       .subscribe(System.out::println);


  }

}
