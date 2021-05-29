package com.reactive.programming.sinks;

import java.util.function.Consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class SlackMember {
  private String name;
  private Consumer<String> messageConsumer;

  public SlackMember(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Consumer<String> getMessageConsumer() {
    return messageConsumer;
  }

  public void setMessageConsumer(Consumer<String> messageConsumer) {
    this.messageConsumer = messageConsumer;
  }

  public void receiveMessage(String message) {
    System.out.println(message);
  }

  public void postMessage(String message) {
    messageConsumer.accept(message);
  }

}
