package com.reactive.programming.backpressure;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Drop {
  public static void main(String[] args) {

    List<Object> list = new ArrayList<>();

    //75% of 16
    System.setProperty("reactor.bufferSize.small", "16");

    Flux
        .create(fluxSink -> {
          for (int i = 0; i < 500; i++) {
            fluxSink.next(i);
            System.out.println("Pushed: " + i);
          }
          fluxSink.complete();
        })
        .onBackpressureDrop(list::add) // add the dropped elements to list
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> {
          sleep(10);
        })
        .subscribe(new DefaultSubscriber("Subscriber"));

    sleep(10000);
    System.out.println(list.size());
  }

  private static void sleep(int time) {
    try {
      Thread.sleep(time);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
