package com.reactive.programming.retry;

import java.time.Duration;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class RetryTimeDelay {

  public static void main(String[] args) throws InterruptedException {

    getIntegers()
        .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(2)))
        .subscribe(System.out::println);

    Thread.currentThread().join();

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
