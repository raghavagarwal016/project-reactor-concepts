package com.reactive.programming.hot.cold.publisher;

import java.time.Duration;
import java.util.stream.Stream;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class HotPublisherRefCount {

  public static void main(String[] args) throws InterruptedException {

    //share=publish().refCount(1)
    Flux<String> flux = Flux
        .fromStream(() -> getMovie())
        .delayElements(Duration.ofSeconds(2))
        .publish()
        .refCount(2); // wil emitt only when 2 subscriber are available

    flux
        .subscribe(new DefaultSubscriber("Sam"));

    Thread.sleep(5000);

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
