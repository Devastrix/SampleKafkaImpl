package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic {
  private final String topicName;
  private final Map<Consumer, Integer> subscribersOffset = new HashMap<>();
  private final List<Consumer> subscribers = new ArrayList<>();
  private final Queue<String> queue;
  private final String[] arr;
  private final int capacity;
  private int size;

  public Topic(String topicName, int capacity) {
    this.topicName = topicName;
    this.capacity = capacity;
    this.arr = new String[capacity];
    this.size = 0;
    this.queue = new QueueImpl(capacity);
  }

  public boolean subscribe(Consumer consumer) {
    this.subscribers.add(consumer);
    this.subscribersOffset.put(consumer, 0);
    publishExistingMessages(consumer);
    return true;
  }

  private void publishExistingMessages(Consumer consumer) {
    int offset = 0;
    while (offset < size) {
      String message = arr[offset];
      if (consumer.consume(message)) {
        offset++;
        subscribersOffset.put(consumer, offset);
      }
    }
  }

  public boolean unSubscribe(Consumer consumer) {
    this.subscribersOffset.remove(consumer);
    return this.subscribers.remove(consumer);
  }

  public void push(String message) {
    publishMessageToAllSubscribers(message);
  }

  private void publishMessageToAllSubscribers(String message) {
    if (size < this.capacity) {
      arr[size++] = message;
      this.subscribers.forEach(c -> {
        int offset = size - 1;
        if (c.consume(message)) {
          offset++;
          subscribersOffset.put(c, offset);
        }
      });
    }
  }
}
