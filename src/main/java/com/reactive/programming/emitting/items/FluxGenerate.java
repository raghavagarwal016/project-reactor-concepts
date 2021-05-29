package com.reactive.programming.emitting.items;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class FluxGenerate {

  public static void main(String[] args) {

    //synchronousSink allows only 1 item to emit
    Flux
        .generate(synchronousSink -> {
          synchronousSink.next(Faker.instance().name().fullName());
          synchronousSink.next(Faker.instance().name().fullName()); // wil give error
        })
        .subscribeWith(new DefaultSubscriber("Generate"));

    Flux
        .generate(synchronousSink -> {
          synchronousSink.next(Faker.instance().name().fullName()); // generate infinite names
        })
        .take(5) //to exit infinite emitter
        .subscribeWith(new DefaultSubscriber("Name"));

    Flux
        .generate(synchronousSink -> {
          String countryName = Faker.instance().country().name();
          synchronousSink.next(countryName);
          if (countryName.equalsIgnoreCase("canada")) {
            synchronousSink.complete();
          }
        })
        .subscribeWith(new DefaultSubscriber("Country"));



  }

}
