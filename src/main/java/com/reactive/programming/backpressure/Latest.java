package com.reactive.programming.backpressure;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Latest {

  public static void main(String[] args) {

    Flux
        .create(fluxSink -> {
          for (int i = 0; i < 500; i++) {
            fluxSink.next(i);
            System.out.println("Pushed: " + i);
          }
          fluxSink.complete();
        })
        .onBackpressureLatest()
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> {
          sleep(10);
        })
        .subscribe(new DefaultSubscriber("Subscriber"));

    sleep(60000);
  }

  private static void sleep(int time) {
    try {
      Thread.sleep(time);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
