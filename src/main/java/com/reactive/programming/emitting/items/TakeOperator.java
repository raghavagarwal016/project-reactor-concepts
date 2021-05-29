package com.reactive.programming.emitting.items;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class TakeOperator {

  public static void main(String[] args) {

    Flux
        .range(1, 10)
        .log()
        .take(3) // cancels the subscription after 3 items are emitted and flux stops emitting the items
        .log()
        .subscribeWith(new DefaultSubscriber("Take"));

    Flux.create(fluxSink -> {
      String countryName = "";
      do {
        countryName = Faker.instance().country().name();
        System.out.println("Emitting : " + countryName);
        fluxSink.next(countryName);
      } while (!countryName.equalsIgnoreCase("canada"));
      fluxSink.complete();
    })
        .take(3) // will cancel the subscription after 3 items but still the flux will emitt the items
        .subscribeWith(new DefaultSubscriber("Country"));

    Flux.create(fluxSink -> {
      String countryName = "";
      do {
        countryName = Faker.instance().country().name();
        System.out.println("Emitting : " + countryName);
        fluxSink.next(countryName);
      } while (!countryName.equalsIgnoreCase("canada") && !fluxSink.isCancelled());
      //fluxSink.isCancelled() used to check is susbscription is cancelled.
      fluxSink.complete();
    })
        .take(3) // will cancel the subscription after 3 items and will not emitt any data.
        .subscribeWith(new DefaultSubscriber("Country"));


  }

}
