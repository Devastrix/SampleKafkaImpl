package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

  @Autowired
  private Broker broker;

  @Test
  void contextLoads() {
  }


  @Test
  void testBroker() {
    Publisher<String> publisher = new PublisherImpl(broker);
    publisher.publish("movies", "batman");
    publisher.publish("movies", "aquaman");
    publisher.publish("movies", "superman");

    System.out.println("Initializing Consumer 1");
    Consumer<String> consumer1 = message -> {
      System.out.println("Consumer 1 => " + message);
      return true;
    };
    broker.subscribe("movies", consumer1);

    System.out.println("Initializing Consumer 2");
    Consumer<String> consumer2 = message -> {
      System.out.println("Consumer 2 => " + message);
      return true;
    };
    broker.subscribe("movies", consumer2);

    System.out.println("Publishing some more...");
    publisher.publish("movies", "flash");
    System.out.println("Unsubscribing consumer 1 ");
    broker.unSubscribe("movies", consumer1);
    System.out.println("Publishing some more...");
    publisher.publish("movies", "wonder woman");
  }
}
