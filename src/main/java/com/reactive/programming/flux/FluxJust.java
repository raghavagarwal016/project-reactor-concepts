package com.reactive.programming.flux;

import reactor.core.publisher.Flux;

public class FluxJust {

  public static void main(String[] args) {

    Flux<String> nameFlux = Flux.just("Raghav Agarwal", "Parrth Dixit", "Tauseef Iqbal");

    nameFlux.subscribe(System.out::println, System.out::println, () -> System.out.println("Process Completed"));

  }

}
