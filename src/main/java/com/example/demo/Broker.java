package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class Broker {
  private final Map<String, Topic> map = new HashMap<>();

  public void publishToTopic(String topicName, String object) {
    Topic topic = map.computeIfAbsent(topicName, s -> new Topic(s, 10));
    topic.push(object);
  }

  public <T> void subscribe(String topicName, Consumer<T> consumer) {
    Topic topic = Optional.ofNullable(map.get(topicName)).orElseThrow(RuntimeException::new);
    topic.subscribe(consumer);
  }

  public <T> void unSubscribe(String topicName, Consumer<T> consumer) {
    Topic topic = Optional.ofNullable(map.get(topicName)).orElseThrow(RuntimeException::new);
    topic.unSubscribe(consumer);
  }
}
