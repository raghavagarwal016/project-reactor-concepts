package com.reactive.programming.context;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ContextDemo {
  public static void main(String[] args) {

    getWelcomeMessage()
        .contextWrite(context -> context.put("user", context.get("user").toString().toUpperCase()))
        .contextWrite(Context.of("user", "Sam"))
        .subscribe(System.out::println);

  }

  private static Mono<String> getWelcomeMessage() {
    return Mono
        .deferContextual(contextView -> {
          if (contextView.hasKey("user")) {
            return Mono.fromSupplier(() -> "Welcome " +  contextView.get("user"));
          }
          else {
           return Mono.error(new RuntimeException("Invalid user"));
          }
        });
  }
}
