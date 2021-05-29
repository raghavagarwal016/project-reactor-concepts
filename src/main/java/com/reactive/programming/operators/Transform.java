package com.reactive.programming.operators;

import java.util.function.Function;

import reactor.core.publisher.Flux;

public class Transform {

  public static void main(String[] args) {

    getPersons()
        .transform(applyTransform())
        .subscribe(System.out::println);

  }

  private static Flux<Person> getPersons() {
    return Flux.range(1, 10).map(i -> new Person());
  }

  private static Function<Flux<Person>, Flux<Person>> applyTransform() {
    return personFlux -> personFlux.doOnNext(person -> person.setName(person.getName().toUpperCase()));
  }

}
