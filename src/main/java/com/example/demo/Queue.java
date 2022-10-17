package com.example.demo;

public interface Queue<T> {
  void push(T object);

  T pop();

  T peek();

  boolean isFull();

  boolean isEmpty();

}
