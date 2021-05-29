package com.reactive.programming.operators;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;


public class FlatMap {

  private static Map<Integer, List<Order>> orderDb = new HashMap<>();

  static {
    List<Order> orders1 = Arrays.asList(
      new Order(1),
      new Order(1),
      new Order(1)
    );
    List<Order> orders2 = Arrays.asList(
        new Order(2),
        new Order(2),
        new Order(2)
    );
    orderDb.put(1, orders1);
    orderDb.put(2, orders2);
  }

  public static void main(String[] args) throws InterruptedException {

    getUsers()
        .flatMap(user -> getOrders(user.getUserId())) //flat map
        .subscribe(System.out::println);

    getUsers()
        .concatMap(user -> getOrders(user.getUserId())) // concat map (operates only when first publisher is drained)
        .subscribe(System.out::println);

    Thread.currentThread().join();

  }

  private static Flux<User> getUsers() {
    return Flux
        .range(1, 2)
        .map(i -> new User(i));
  }

  private static Flux<Order> getOrders(int userId) {
    return Flux
        .create((FluxSink<Order> orderFluxSink) -> {
          orderDb.get(userId).forEach(orderFluxSink::next);
          orderFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
  }

}
