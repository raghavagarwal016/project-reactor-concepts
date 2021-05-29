package com.reactive.programming.batching;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class OrderService {
  private static List<String> allowedCategories = Arrays.asList("Kids", "Automotive");

  public static void main(String[] args) throws InterruptedException {

    getOrders()
        .filter(order -> allowedCategories.contains(order.getCategory()))
        .groupBy(order -> order.getCategory())
        .subscribe(stringOrderGroupedFlux -> process(stringOrderGroupedFlux, stringOrderGroupedFlux.key()));

    Thread.currentThread().join();

  }

  private static void process(Flux<Order> orders, String key) {
    orders
        .subscribe(order -> {
          if (allowedCategories.get(0).equalsIgnoreCase(key)) {
            double finalPrice = order.getPrice() / 2;
            Order freeOrder =
                new Order(allowedCategories.get(0), Faker.instance().random().nextInt(100, 500));
            System.out.println("Order Proccessed. Final Price: " + finalPrice + " Free Order: " + freeOrder);
          }
          else {
            double finalPrice = order.getPrice() * 1.1;
            System.out.println("Packaging order");
            System.out.println("Order Proccessed. Final Price: " + finalPrice);
          }
        });
  }

  private static Flux<Order> getOrders() {
    return Flux
        .interval(Duration.ofMillis(100))
        .map(i -> new Order(Faker.instance().commerce().department(), Faker.instance().random().nextInt(100, 500)));

  }
}
