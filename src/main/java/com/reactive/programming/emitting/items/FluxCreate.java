package com.reactive.programming.emitting.items;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class FluxCreate {
  public static void main(String[] args) throws InterruptedException {
    DefaultSubscriber defaultSubscriber = new DefaultSubscriber("Flux Create");

    Flux.create(fluxSink -> {
      fluxSink.next(1);
      fluxSink.next(2);
      fluxSink.next(3);
      fluxSink.complete();
    }).subscribeWith(defaultSubscriber);

    Flux.create(fluxSink -> {
      String countryName = "";
      do {
        countryName = Faker.instance().country().name();
        fluxSink.next(countryName);
      } while (!countryName.equalsIgnoreCase("canada"));
      fluxSink.complete();
    }).subscribeWith(defaultSubscriber);

  }
}
