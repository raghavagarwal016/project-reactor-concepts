package com.reactive.programming.emitting.items;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultSubscriber implements Subscriber<Object> {

  private String name;

  @Override
  public void onSubscribe(Subscription subscription) {
    subscription.request(Integer.MAX_VALUE);
  }

  @Override
  public void onNext(Object o) {
    System.out.println("Received :" + o);
  }

  @Override
  public void onError(Throwable throwable) {
    System.out.println(name + ", Error :" + throwable.getMessage());
  }

  @Override
  public void onComplete() {
    System.out.println(name + " completed");
  }
}
