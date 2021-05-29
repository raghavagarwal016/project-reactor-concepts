package com.reactive.programming.hot.cold.publisher;

import java.time.Duration;
import java.util.stream.Stream;

import com.reactive.programming.emitting.items.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class HotPublisherAutoConnect {

  public static void main(String[] args) throws InterruptedException {

    Flux<String> flux = Flux
        .fromStream(() -> getMovie())
        .delayElements(Duration.ofSeconds(2))
        .publish()
        .autoConnect(0); // will start emitting without any sbuscriber

    Thread.sleep(3000);

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
