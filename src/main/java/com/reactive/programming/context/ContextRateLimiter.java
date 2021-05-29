package com.reactive.programming.context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.github.javafaker.Faker;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ContextRateLimiter {

  private static Map<String, String> userMap;
  private static Map<String, Integer> bookMap;

  static {
    userMap = new HashMap<>();
    userMap.put("Sam", "std");
    userMap.put("Mike", "prime");

    bookMap = new HashMap<>();
    bookMap.put("std", 2);
    bookMap.put("prime", 3);
  }

  public static void main(String[] args) {

      getBook()
          .repeat(2)
          .contextWrite(userCategoryContext())
          .contextWrite(Context.of("user", "Mike"))
          .subscribe(System.out::println);

    getBook()
        .repeat(1)
        .contextWrite(userCategoryContext())
        .contextWrite(Context.of("user", "Sam"))
        .subscribe(System.out::println);

  }

  public static Mono<String> getBook() {
    return Mono.deferContextual(contextView -> {
      if (contextView.get("allow")) {
        return Mono.fromSupplier(() -> Faker.instance().book().title());
      } else {
        return Mono.error(new RuntimeException("Not Allowed"));
      }
    }).contextWrite(rateLimiterContext());
  }

  private static Function<Context, Context> userCategoryContext() {
    return context -> {
      String user = context.get("user").toString();
      String category = userMap.get(user);
      return context.put("category", category);
    };
  }

  private static Function<Context, Context> rateLimiterContext() {
    return context -> {
      if (context.hasKey("category")) {
        String category = context.get("category").toString();
        Integer attempts = bookMap.get(category);
        if (attempts > 0) {
          bookMap.put(category, attempts - 1);
          return context.put("allow", true);
        }
      }
      return context.put("allow", false);
    };
  }



}
