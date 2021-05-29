package com.reactive.programming.flux;

import java.time.Duration;
import java.util.Random;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;

public class Stocks {

  private static Integer INITAL_PRICE = 100;

  public static void main(String[] args) throws InterruptedException {

    Flux
        .interval(Duration.ofSeconds(1))
        .map(i -> changeStockPrice())
        .subscribeWith(new StocksSubscriber());

    Thread.currentThread().join();

  }

  private static Integer changeStockPrice() {
    int changedPrice = generateRandomNumber();
    INITAL_PRICE = INITAL_PRICE - changedPrice;
    return INITAL_PRICE;
  }

  private static int generateRandomNumber() {
    Random random = new Random();
    return random.ints(-5, 5)
        .findFirst()
        .getAsInt();

  }
}

class StocksSubscriber implements Subscriber<Integer> {

  private Subscription subscription;

  @Override
  public void onSubscribe(Subscription subscription) {
    this.subscription = subscription;
    this.subscription.request(10);
  }

  @Override
  public void onNext(Integer o) {
    System.out.println(o);
    if (o < 98) {
      this.subscription.cancel();
    }
  }

  @Override
  public void onError(Throwable throwable) {
    System.out.println("error");
  }

  @Override
  public void onComplete() {
    System.out.println("Process Completed");
  }
}