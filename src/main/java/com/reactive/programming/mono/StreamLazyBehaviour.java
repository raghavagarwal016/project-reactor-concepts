package com.reactive.programming.mono;

import java.util.stream.Stream;

// Stream is lazy. Means the execution is not invoked until it finds a terminating operation.
public class StreamLazyBehaviour {
  public static void main(String[] args) {
    // create a stream pipeline to double a number with time delay of 5 secs
    Stream<Integer> stream = Stream.of(1).map(i -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return i * 2;
    });

    // will print the reference of stream instantly
    System.out.println(stream);

    // will print 2 after 5secs as stream pipeline is invoked because forEach is a terminating operation
    stream.forEach(System.out::println);


  }
}
