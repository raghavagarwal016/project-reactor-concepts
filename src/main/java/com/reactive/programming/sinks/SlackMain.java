package com.reactive.programming.sinks;

public class SlackMain {
  public static void main(String[] args) throws InterruptedException {

    SlackRoom slackRoom = new SlackRoom("Project Reactor");

    SlackMember sam = new SlackMember("Sam");
    SlackMember jake = new SlackMember("Jake");
    SlackMember mike = new SlackMember("Mike");

    slackRoom.joinMember(sam);
    slackRoom.joinMember(jake);

    sam.postMessage("Hi! all....");

    Thread.sleep(3000);

    jake.postMessage("Hey");
    sam.postMessage("How are you Jake");

    Thread.sleep(3000);

    slackRoom.joinMember(mike);

    mike.postMessage("Hi Sam and Jake. How are you");




  }
}
