package com.reactive.programming.retry;

import java.time.Duration;

import com.github.javafaker.Faker;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class RetrySpec {

  public static void main(String[] args) throws InterruptedException {
    orderService(Faker.instance().business().creditCardNumber()).doOnError(err -> System.out.println(err.getMessage()))
        .retryWhen(Retry.from(retrySignalFlux -> {
          return retrySignalFlux
              .doOnNext(retrySignal -> System.out.println(retrySignal.totalRetries() + ", " + retrySignal.failure()))
              .handle((retrySignal, synchronousSink) -> {
                if (retrySignal.failure().getMessage().equals("500")) {
                  synchronousSink.next(1);
                } else {
                  synchronousSink.error(retrySignal.failure());
                }
              }).delayElements(Duration.ofSeconds(1));
        })).subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));

    Thread.currentThread().join();

  }

  //order service
  private static Mono<String> orderService(String ccNumber) {
    return Mono
              .fromSupplier(() -> {
                processPayment(ccNumber);
                return Faker.instance().idNumber().valid();
              });
  }

  //payment service
  private static void processPayment(String ccNumber) {
    int random = Faker.instance().random().nextInt(1, 10);
    if (random < 8){
      throw new RuntimeException("500");
    }
    else if (random < 10) {
      throw new RuntimeException("404");
    }
  }

}
