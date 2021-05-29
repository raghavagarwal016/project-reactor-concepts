package com.reactive.programming.sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {
  private String roomName;
  private Sinks.Many<SlackMessage> sink;
  private Flux<SlackMessage> flux;

  public SlackRoom(String roomName) {
    this.roomName = roomName;
    this.sink = Sinks.many().replay().all();
    this.flux = this.sink.asFlux();
  }

  public void joinMember(SlackMember slackMember) {
    System.out.println(slackMember.getName() + "-------joined-------" + this.roomName);
    revieveMessage(slackMember);
    slackMember.setMessageConsumer(message -> postMessage(message, slackMember));
  }

  private void revieveMessage(SlackMember slackMember) {
    this.flux
        .doOnNext(slackMessage -> slackMessage.setReceiverName(slackMember.getName()))
        .map(SlackMessage::toString)
        .subscribe(System.out::println);
  }

  private void postMessage(String message, SlackMember slackMember) {
    SlackMessage slackMessage = new SlackMessage();
    slackMessage.setSenderName(slackMember.getName());
    slackMessage.setMessage(message);
    sink.tryEmitNext(slackMessage);
  }

}
