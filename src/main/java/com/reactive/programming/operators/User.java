package com.reactive.programming.operators;

import com.github.javafaker.Faker;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

  private int userId;
  private String username;

  public User(int userId) {
    this.userId = userId;
    this.username = Faker.instance().name().username();
  }


}
