package com.reactive.programming.combining.publisher;

import com.github.javafaker.Faker;
import com.reactive.programming.emitting.items.DefaultSubscriber;

import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Flux;

public class StartWith {

  private static List<String> list = new ArrayList<>();

  public static void main(String[] args) {
    generateName()
        .take(2)
        .subscribe(new DefaultSubscriber("Subscriber-1"));

    generateName()
        .take(2)
        .subscribe(new DefaultSubscriber("Subscriber-2"));

    generateName()
        .take(3)
        .subscribe(new DefaultSubscriber("Subscriber-3"));

    generateName()
        .filter(s -> s.startsWith("A"))
        .take(1)
        .subscribe(new DefaultSubscriber("Subscriber-4"));

  }

  private static Flux<String> generateName() {
    return Flux.generate(stringSynchronousSink -> {
      System.out.println("Generated new");
      sleep(1000);
      String name = Faker.instance().name().fullName();
      list.add(name);
      stringSynchronousSink.next(name);
    }).cast(String.class).startWith(getNameFromCache()); //start with combine the publisher
  }

  private static Flux<String> getNameFromCache() {
    return Flux.fromIterable(list);
  }

  private static void sleep(int time) {
    try {
      Thread.sleep(time);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
