package com.reactive.programming.batching;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class BookRevenue {

  private static List<String> allowedCategories = Arrays.asList("Science Fiction", "Fantasy", "Suspense/Thriller");

  public static void main(String[] args) throws InterruptedException {

    getBookOrders()
        .filter(book -> allowedCategories.contains(book.getCategory()))
        .buffer(10)
        .map(books -> calculateRevenue(books))
        .subscribe(System.out::println);

    Thread.currentThread().join();

  }

  private static Map<String, Integer> calculateRevenue(List<Book> books) {
    return books.stream().collect(Collectors.groupingBy(Book::getCategory, Collectors.summingInt(Book::getPrice)));
  }

  private static Flux<Book> getBookOrders() {
    return Flux
        .interval(Duration.ofMillis(100))
        .map(i -> new Book(Faker.instance().book().genre(), Faker.instance().random().nextInt(100, 500)));

  }
}
