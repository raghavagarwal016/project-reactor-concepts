package com.reactive.programming.sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkThreadSafety {
  public static void main(String[] args) throws InterruptedException {

    Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
    Flux<Object> flux = sink.asFlux();

    List<Object> list = new ArrayList<>();
    flux.subscribe(list::add);

//    //This is not thread safe
//    for (int i = 0; i < 1000; i++) {
//      final int j = i;
//      CompletableFuture.runAsync(() -> {
//        sink.tryEmitNext(j);
//      });
//    }
//    sink.tryEmitComplete();
//    Thread.sleep(3000);
//    System.out.println(list.size());
//

    //This is thread safe. As we are giving error handlder
    for (int i = 0; i < 1000; i++) {
      final int j = i;
      CompletableFuture.runAsync(() -> {
        sink.emitNext(j, (s, e) -> true);
      });
    }
    sink.tryEmitComplete();

    Thread.sleep(3000);
    System.out.println(list.size());


  }
}
