package com.example.demo;

public interface Publisher<T> {
  void publish(String topicName, T object);
}
