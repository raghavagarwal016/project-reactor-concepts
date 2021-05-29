package com.reactive.programming.emitting.items;

import java.util.concurrent.atomic.AtomicInteger;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class FluxGenerateCounter {

  public static void main(String[] args) {

    AtomicInteger counter = new AtomicInteger();

    Flux.generate(synchronousSink -> {
      counter.getAndIncrement();
      String countryName = Faker.instance().country().name();
      synchronousSink.next(countryName);
      if (countryName.equalsIgnoreCase("canada") || counter.get() >= 10) {
        synchronousSink.complete();
      }
    }).subscribeWith(new DefaultSubscriber("Country"));

    Flux.generate(() -> 1, (count, synchronousSink) -> {
      counter.getAndIncrement();
      String countryName = Faker.instance().country().name();
      synchronousSink.next(countryName);
      if (countryName.equalsIgnoreCase("canada") || count >= 10) {
        synchronousSink.complete();
      }
      return count + 1;
    }).subscribeWith(new DefaultSubscriber("Country"));

  }

}
