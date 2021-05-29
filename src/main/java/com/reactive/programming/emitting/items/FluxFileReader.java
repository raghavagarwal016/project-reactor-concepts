package com.reactive.programming.emitting.items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.Stream;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SynchronousSink;

public class FluxFileReader {

  private static String FILE_PATH = "/Users/raghavagarwal/Desktop/workspace/ProjectReactor/src/main/resources/file5.txt";

  public static void main(String[] args) {

    FileReaderService fileReaderService = new FileReaderService(FILE_PATH);

    Flux
        .create(fileReaderService)
        .subscribeWith(new DefaultSubscriber("File Reader"));


  }

}

class FileReaderService implements Consumer<FluxSink> {

  private String filePath;
  private FluxSink fluxSink;

  public FileReaderService(String filePath) {
    this.filePath = filePath;
  }

  public FileReaderService() {

  }

  @SneakyThrows
  @Override
  public void accept(FluxSink fluxSink) {
    this.fluxSink = fluxSink;
    File file = new File(filePath);
    Stream<String> linesStream = Files.lines(file.toPath());
    linesStream.forEach(line -> {
      fluxSink.next(line);
    });
  }

}
