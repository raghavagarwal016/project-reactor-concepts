package com.reactive.programming.batching;

import java.time.Duration;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class OverlapAndDrop {
  public static void main(String[] args) throws InterruptedException {

    eventStream()
        .buffer(3, 2) // remove the oldest 2 item and right replace it with new items
        .subscribe(new DefaultSubscriber("Buffer Batching"));

    Thread.currentThread().join();

  }

  private static Flux<String> eventStream() {
    return Flux
        .interval(Duration.ofMillis(10))
        .map(i -> "event: " + i);
  }
}
