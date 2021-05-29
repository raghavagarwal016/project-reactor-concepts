package com.reactive.programming.retry;

import java.util.concurrent.atomic.AtomicInteger;

import javax.rmi.CORBA.Util;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class Retry {

  public static void main(String[] args) {

    getIntegers()
        .retry(2) //will connect 2 more times. so total 3 times in case of error
        .subscribe(System.out::println);

  }

  private static Flux<Integer> getIntegers() {
    return Flux
        .range(1, 3)
        .doOnSubscribe(subscription -> System.out.println("Subscribed"))
        .doOnComplete(() -> System.out.println("Completed"))
        .map(i -> i / (Faker.instance().random().nextInt(1, 5) < 3 ? 0 : 1))
        .doOnError(err -> System.out.println(err.getMessage()));

  }

}
