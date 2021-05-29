package com.reactive.programming.mono;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FileOperations {

  private static String fileName1 = "/Users/raghavagarwal/Desktop/workspace/ProjectReactor/src/main/resources/file1.txt";
  private static String fileName2 = "/Users/raghavagarwal/Desktop/workspace/ProjectReactor/src/main/resources/file2.txt";
  private static String fileName3 = "/Users/raghavagarwal/Desktop/workspace/ProjectReactor/src/main/resources/file3.txt";
  private static String fileName4 = "/Users/raghavagarwal/Desktop/workspace/ProjectReactor/src/main/resources/file4.txt";
  private static String content = "Files in Java might be tricky, but it is fun enough!";

  public static void main(String[] args) throws InterruptedException {

    Mono
        .fromRunnable(getFileContents(fileName1))
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(null, System.out::println, onComplete());

    Mono
        .fromRunnable(getFileContents(fileName4))
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(null, System.out::println, onComplete());

    Mono
        .fromRunnable(writeFileContents(fileName3, content))
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(null, System.out::println, onComplete());

    Mono
        .fromRunnable(deleteFile(fileName2))
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(null, System.out::println, onComplete());

    Thread.currentThread().join();

  }

  private static Runnable getFileContents(String filePath) {
    return new Runnable() {
      @SneakyThrows
      @Override
      public void run() {
        System.out.println("Starting read operation");
        File file = new File(filePath);
          Scanner scanner = new Scanner(file);
          while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            System.out.print(data);
          }
          scanner.close();
        }
    };
  }

  private static Runnable deleteFile(String filePath) {
    return new Runnable() {
      @SneakyThrows
      @Override
      public void run() {
        System.out.println("Starting delete operation");
        File myObj = new File(filePath);
        if (myObj.delete()) {
          System.out.print("Deleted the file");
        } else {
          System.out.print("Failed to delete the file.");
        }
      }
    };
  }

  private static Runnable writeFileContents(String filePath, String content) {
    return new Runnable() {
      @SneakyThrows
      @Override
      public void run() {
        System.out.println("Starting write operation");
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(content);
        fileWriter.close();
        System.out.print("Content written to file");
      }
    };
  }

  private static Runnable onComplete() {
    return () -> System.out.println("--->Process Completed");
  }

}
