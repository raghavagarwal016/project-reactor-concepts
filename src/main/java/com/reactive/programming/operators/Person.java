package com.reactive.programming.operators;

import com.github.javafaker.Faker;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {
  private String name;
  private int age;

  public Person() {
    this.name = Faker.instance().name().fullName();
    this.age = Faker.instance().random().nextInt(1, 30);
  }
}
