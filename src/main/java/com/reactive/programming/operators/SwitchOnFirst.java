package com.reactive.programming.operators;

import java.util.function.Function;

import reactor.core.publisher.Flux;

public class SwitchOnFirst {

    public static void main(String[] args) {

      getPersons()
          .switchOnFirst((signal, personFlux) -> {
           if(signal.isOnNext() && signal.get().getAge() > 10) {
             return personFlux;
           }
           else {
            return applyTransform().apply(personFlux);
           }
          })
          .subscribe(System.out::println);

    }

    private static Flux<Person> getPersons() {
      return Flux.range(1, 10).map(i -> new Person());
    }

    private static Function<Flux<Person>, Flux<Person>> applyTransform() {
      return personFlux -> personFlux.doOnNext(person -> person.setName(person.getName().toUpperCase()));
    }

}
