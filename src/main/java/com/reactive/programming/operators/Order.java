package com.reactive.programming.operators;

import com.github.javafaker.Faker;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {

  private String item;
  private String price;
  private int userId;

  public Order(int userId) {
    this.userId = userId;
    this.item = Faker.instance().commerce().productName();
    this.price = Faker.instance().commerce().price();
  }
}
