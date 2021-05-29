package com.reactive.programming.sinks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SlackMessage {
  private String senderName;
  private String receiverName;
  private String message;
}
