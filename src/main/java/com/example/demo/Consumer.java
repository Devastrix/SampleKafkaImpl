package com.example.demo;

public interface Consumer<T> {
  boolean consume(String message);
}
