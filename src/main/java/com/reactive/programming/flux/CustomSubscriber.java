package com.reactive.programming.flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class CustomSubscriber implements Subscriber {

  public static void main(String[] args) {

    Flux<String> names = Flux
        .range(1, 20)
        .map(i -> Faker.instance().name().fullName());

    CustomSubscriber customSubscriber = new CustomSubscriber();
    names.subscribeWith(customSubscriber);


  }

  @Override
  public void onSubscribe(Subscription subscription) {
    System.out.println("On subscription invoked");
    subscription.request(3);
    subscription.request(3);
    subscription.request(3);
    subscription.request(4);
    subscription.cancel();
    subscription.request(3);// will not work after cancel
  }

  @Override
  public void onNext(Object o) {
    System.out.println(o);
  }

  @Override
  public void onError(Throwable throwable) {
    System.out.println(throwable.getStackTrace());
  }

  @Override
  public void onComplete() {
    System.out.println("Process Completed");
  }
}
