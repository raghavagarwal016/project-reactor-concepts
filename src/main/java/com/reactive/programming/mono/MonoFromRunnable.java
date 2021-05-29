package com.reactive.programming.mono;

import com.github.javafaker.Faker;

import reactor.core.publisher.Mono;

public class MonoFromRunnable {

  public static void main(String[] args) {
    Mono.fromRunnable(getName())
        .subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));
  }

  private static Runnable getName() {
    sleep();
    return () -> System.out.println(Faker.instance().name().fullName());
  }

  private static void sleep() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
