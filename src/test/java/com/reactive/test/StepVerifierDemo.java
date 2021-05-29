package com.reactive.test;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.reactive.programming.batching.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class StepVerifierDemo {

  @Test
  public void stepVerifierTest1() {

    Flux<Integer> flux = Flux.just(1, 2, 3);

    StepVerifier.create(flux)
        .expectNext(1)
        .expectNext(2)
        .expectNext(3)
        .expectComplete()
        .verify();
  }

  @Test
  public void stepVerifierTest2() {

    Flux<Integer> flux = Flux.just(1, 2, 3);

    StepVerifier.create(flux)
        .expectNext(1, 2, 3)
        .expectComplete()
        .verify();
  }

  @Test
  public void stepVerifierTest3() {

    Flux<Integer> flux = Flux.just(1, 2, 3);

    StepVerifier.create(flux)
        .expectNext(1, 2, 3)
        .verifyComplete();
  }

  @Test
  public void stepVerifierErrorTest1() {

    Flux<Integer> flux = Flux.just(1, 2, 3);
    Flux<Integer> fluxError = Flux.error(new RuntimeException("Not Valid"));
    Flux<Integer> concat = Flux.concat(flux, fluxError);

    StepVerifier.create(concat)
        .expectNext(1, 2, 3)
        .verifyError();
  }

  @Test
  public void stepVerifierErrorTest2() {

    Flux<Integer> flux = Flux.just(1, 2, 3);
    Flux<Integer> fluxError = Flux.error(new RuntimeException("Not Valid"));
    Flux<Integer> concat = Flux.concat(flux, fluxError);

    StepVerifier.create(concat)
        .expectNext(1, 2, 3)
        .verifyError(RuntimeException.class);
  }

  @Test
  public void stepVerifierErrorTest3() {

    Flux<Integer> flux = Flux.just(1, 2, 3);
    Flux<Integer> fluxError = Flux.error(new RuntimeException("Not Valid"));
    Flux<Integer> concat = Flux.concat(flux, fluxError);

    StepVerifier.create(concat)
        .expectNext(1, 2, 3)
        .verifyErrorMessage("Not Valid");
  }

  @Test
  public void stepVerifierRangeTest1() {

    Flux<Integer> flux = Flux.range(1, 50);

    StepVerifier.create(flux)
        .expectNextCount(50)
        .verifyComplete();
  }

  @Test
  public void stepVerifierRangeTest2() {

    Flux<Integer> flux = Flux.range(1, 50);

    StepVerifier.create(flux)
        .thenConsumeWhile(i -> i < 100)
        .verifyComplete();
  }

  @Test
  public void stepVerifierDelayTest1() {

    Mono<Book> mono = Mono.fromSupplier(() -> new Book()).delayElement(Duration.ofSeconds(3));

    StepVerifier.create(mono)
        .assertNext(book -> Assertions.assertNotNull(book))
        .expectComplete()
        .verify(Duration.ofSeconds(4));
  }

  @Test
  public void stepVerifierVirtualTimeTest1() {

    Flux<String> flux = Flux.range(1, 4).delayElements(Duration.ofSeconds(5)).map(integer -> integer + "a");

    StepVerifier.withVirtualTime(() -> flux)
        .thenAwait(Duration.ofSeconds(30))
        .expectNext("1a", "2a", "3a", "4a")
        .verifyComplete();

  }

  @Test
  public void stepVerifierVirtualTimeTest2() {

    Flux<String> flux = Flux.range(1, 4).delayElements(Duration.ofSeconds(5)).map(integer -> integer + "a");

    StepVerifier.withVirtualTime(() -> flux)
        .expectSubscription()
        .expectNoEvent(Duration.ofSeconds(4))
        .thenAwait(Duration.ofSeconds(20))
        .expectNext("1a", "2a", "3a", "4a")
        .verifyComplete();

  }

  @Test
  public void stepVerifierScenarioNameTest1() {

    Flux<String> flux = Flux.just("a", "b", "c");

    StepVerifierOptions stepVerifierOptions = StepVerifierOptions.create().scenarioName("alphabets-tests");

    StepVerifier.create(flux, stepVerifierOptions)
        .expectNextCount(12)
        .verifyComplete();

  }

  @Test
  public void stepVerifierScenarioNameTest2() {

    Flux<String> flux = Flux.just("a", "bb", "c");

    StepVerifier.create(flux)
        .expectNext("a")
        .as("a-test")
        .expectNext("b")
        .as("b-test")
        .expectNext("c")
        .as("c-test")
        .verifyComplete();

  }

  @Test
  public void stepVerifierContextTest1() {
  Mono<String> mono =   Mono.deferContextual(contextView -> {
      if (contextView.hasKey("user")) {
        return Mono.just("Welcome " + contextView.get("user"));
      }
      else {
        return Mono.error(new RuntimeException("Invalid User"));
      }
    });

    StepVerifier.create(mono)
        .verifyError(RuntimeException.class);
  }

  @Test
  public void stepVerifierContextTest2() {
    Mono<String> mono =   Mono.deferContextual(contextView -> {
      if (contextView.hasKey("user")) {
        return Mono.just("Welcome " + contextView.get("user"));
      }
      else {
        return Mono.error(new RuntimeException("Invalid User"));
      }
    });

    StepVerifierOptions stepVerifierOptions =
        StepVerifierOptions.create().withInitialContext(Context.of("user", "Sam"));

    StepVerifier.create(mono, stepVerifierOptions)
        .expectNext("Welcome Sam")
        .verifyComplete();
  }
}
