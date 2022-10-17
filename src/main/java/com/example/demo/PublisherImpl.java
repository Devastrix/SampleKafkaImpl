package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class PublisherImpl implements Publisher<String> {
  private final Broker broker;

  public PublisherImpl(Broker broker) {
    this.broker = broker;
  }

  @Override
  public void publish(String topicName, String message) {
    broker.publishToTopic(topicName, message);
  }
}
