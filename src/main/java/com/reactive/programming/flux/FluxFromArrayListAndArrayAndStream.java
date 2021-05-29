package com.reactive.programming.flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;

public class FluxFromArrayListAndArrayAndStream {

  public static void main(String[] args) {

    List<Integer> integers = IntStream.range(1, 11).mapToObj(i -> new Integer(i)).collect(Collectors.toList());
    Integer[] integersArr = new Integer[10];
    IntStream.range(1, 11).forEach(i -> integersArr[i -1] = i);

    //Flux.fromIterable is used to create flux from list
   Flux.fromIterable(integers)
       .subscribe(System.out::println);

    //Flux.fromArray is used to create flux from array
    Flux.fromArray(integersArr)
        .subscribe(System.out::println);

    //Flux.fromStream is used to create flux from supplier of stream
    Flux<Integer> integerFlux = Flux.fromStream(() -> integers.stream());
    integerFlux.subscribe(System.out::println, System.out::println, () -> System.out.println("process completed"));

    //Will print both the subscriber
    integerFlux.subscribe(System.out::println, System.out::println, () -> System.out.println("process completed"));

    //Flux.fromStream is used to create flux from stream
    integerFlux = Flux.fromStream(integers.stream());
    integerFlux.subscribe(System.out::println, System.out::println, () -> System.out.println("process completed"));

    //Will throw error as stream can only be processed once
    integerFlux.subscribe(System.out::println, System.out::println, () -> System.out.println("process completed"));

  }

}
