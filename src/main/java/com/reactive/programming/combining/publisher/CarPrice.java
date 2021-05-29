package com.reactive.programming.combining.publisher;

import java.time.Duration;

import com.github.javafaker.Faker;
import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class CarPrice {

  private static Double carPrice = 10000.0;

  public static void main(String[] args) throws InterruptedException {


    Flux
        .combineLatest(getCarPrice(), getDemandFactor(), (price, factor) -> price * factor)
        .subscribe(new DefaultSubscriber("Car Price"));

    Thread.currentThread().join();
  }

  private static Flux<Double> getCarPrice() {
    return Flux.interval(Duration.ZERO, Duration.ofSeconds(1)).map(i -> carPrice).doOnNext(i -> {
      if (!(i == 1)) {
        carPrice = carPrice - 100;
      }
    });
  }

  private static Flux<Double> getDemandFactor() {
    return Flux.generate(synchronousSink -> {
      synchronousSink.next(Faker.instance().random().nextInt(8, 12) / 10.0);
    }).cast(Double.class).delayElements(Duration.ofSeconds(3)).startWith(1.0);
  }

}
