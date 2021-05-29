package com.reactive.programming.hot.cold.publisher;

import java.time.Duration;
import java.util.stream.Stream;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class HotPublisherCache {

  public static void main(String[] args) throws InterruptedException {

    Flux<String> flux = Flux
        .fromStream(() -> getMovie())
        .delayElements(Duration.ofSeconds(1))
        .cache();

    Thread.sleep(2000);

    flux
        .subscribe(new DefaultSubscriber("Sam"));

    Thread.sleep(10000);

    flux
        .subscribe(new DefaultSubscriber("Mike"));

    Thread.currentThread().join();


  }

  private static Stream<String> getMovie() {
    System.out.println("Got the movie streaming requirement");
    return Stream.of(
        "Secne 1", "Secne 2", "Secne 3",  "Secne 4",  "Secne 5",  "Secne 6",  "Secne 7"
    );
  }

}
