package com.reactive.programming.batching;

import java.time.Duration;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class BufferTimeout {
  public static void main(String[] args) throws InterruptedException {

    eventStream()
        .bufferTimeout(10, Duration.ofSeconds(2)) // will collect 10 times and then publish list of 10 items
        .subscribe(new DefaultSubscriber("Buffer Batching"));

    Thread.currentThread().join();

  }

  private static Flux<String> eventStream() {
    return Flux
        .interval(Duration.ofMillis(10))
        .map(i -> "event: " + i);
  }
}
