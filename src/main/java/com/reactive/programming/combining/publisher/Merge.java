package com.reactive.programming.combining.publisher;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class Merge {

  public static void main(String[] args) throws InterruptedException {

    Flux
        .merge(getIndigoFlights(), getSpiceJetFlights(), getGoAirFlights())
        .subscribe(System.out::println);

    Thread.currentThread().join();

  }

  private static Flux<String> getIndigoFlights() {
    return Flux
        .range(1, Faker.instance().random().nextInt(1, 5))
        .map(i -> "Indigo " + Faker.instance().random().nextInt(100, 999))
        .filter(i -> Faker.instance().random().nextBoolean());
  }

  private static Flux<String> getSpiceJetFlights() {
    return Flux
        .range(1, Faker.instance().random().nextInt(1, 5))
        .map(i -> "SpiceJet " + Faker.instance().random().nextInt(100, 999))
        .filter(i -> Faker.instance().random().nextBoolean());
  }

  private static Flux<String> getGoAirFlights() {
    return Flux
        .range(1, Faker.instance().random().nextInt(1, 5))
        .map(i -> "GoAir " + Faker.instance().random().nextInt(100, 999))
        .filter(i -> Faker.instance().random().nextBoolean());
  }
}
