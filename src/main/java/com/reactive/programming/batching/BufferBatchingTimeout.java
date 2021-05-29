package com.reactive.programming.batching;

import java.time.Duration;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class BufferBatchingTimeout {

  public static void main(String[] args) throws InterruptedException {

    eventStream()
        .buffer(Duration.ofSeconds(2)) // will publish list of items every 2 secs
        .subscribe(new DefaultSubscriber("Buffer Batching"));

    Thread.currentThread().join();

  }

  private static Flux<String> eventStream() {
    return Flux
        .interval(Duration.ofMillis(300))
        .map(i -> "event: " + i);
  }
}
