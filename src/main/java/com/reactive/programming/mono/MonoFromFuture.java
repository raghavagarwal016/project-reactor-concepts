package com.reactive.programming.mono;

import java.util.concurrent.CompletableFuture;

import com.github.javafaker.Faker;

import reactor.core.publisher.Mono;

public class MonoFromFuture {
  public static void main(String[] args) {
    Mono mono = Mono.fromFuture(MonoFromFuture::getName);
    mono.subscribe(System.out::println);
    sleep();
  }

  private static CompletableFuture<String> getName() {
    return CompletableFuture.supplyAsync(() -> Faker.instance().name().fullName());
  }

  private static void sleep() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
