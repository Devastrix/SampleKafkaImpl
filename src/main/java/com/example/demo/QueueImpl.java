package com.example.demo;

public class QueueImpl implements Queue<String> {
  private final String[] list;
  private final int capacity;
  private int front;
  private int end;
  private int count;

  public QueueImpl(int capacity) {
    this.capacity = capacity;
    this.list = new String[capacity];
    this.front = 0;
    this.end = -1;
    this.count = 0;
  }

  @Override
  public void push(String object) {
    if (!isFull()) {
      end++;
      list[end] = object;
      count++;
    }

  }

  @Override
  public String pop() {
    if (!isEmpty()) {
      String x = list[front];
      front++;
      count--;
      return x;
    }
    return null;
  }

  @Override
  public String peek() {
    if (!isEmpty()) {
      return list[front];
    }
    return null;
  }

  @Override
  public boolean isFull() {
    return count == capacity;
  }

  @Override
  public boolean isEmpty() {
    return count == 0;
  }

}
