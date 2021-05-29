package com.reactive.programming.threading;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ParallelExecution {

  public static void main(String[] args) throws InterruptedException {

    List<Integer> integers = new ArrayList<>();
    List<Integer> vector = new Vector<>();

    Flux
        .range(1, 10)
        .parallel()
        .runOn(Schedulers.parallel())
        .doOnNext(i -> printThreadName("Next " + i))
        .subscribe(i -> printThreadName("Sub " + i));

    Flux
        .range(1, 1000)
        .parallel()
        .runOn(Schedulers.parallel())
        .subscribe(i -> {
          integers.add(i);
          vector.add(i);
        });

    Thread.sleep(3000);

    System.out.println(integers.size());
    System.out.println(vector.size());

  }

  private static void printThreadName(String message) {
    System.out.println(message + "\t\t" + "Thread: " + Thread.currentThread().getName());
  }

}
