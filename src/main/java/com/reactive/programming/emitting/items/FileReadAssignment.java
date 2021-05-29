package com.reactive.programming.emitting.items;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class FileReadAssignment {

  private static String FILE_PATH =
      "/Users/raghavagarwal/Desktop/workspace/ProjectReactor/src/main/resources/file5.txt";

  public static void main(String[] args) {

    Path path = Paths.get(FILE_PATH);
    Flux.generate(openReader(path), read(), closeReader()).subscribeWith(new DefaultSubscriber("File Reader"));

  }

  private static Callable<BufferedReader> openReader(Path path) {
    return () -> Files.newBufferedReader(path);
  }

  private static BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
    return ((bufferedReader, stringSynchronousSink) -> {
      try {
        String line = bufferedReader.readLine();
        if (Objects.isNull(line)) {
          stringSynchronousSink.complete();
        }
        else {
          stringSynchronousSink.next(line);
        }
      } catch (IOException e) {
        stringSynchronousSink.error(e);
      }
      return bufferedReader;
    });
  }

  private static Consumer<BufferedReader> closeReader() {
    return bufferedReader -> {
      try {
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    };
  }

}
