package com.example.notification.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "test-message", groupId = "my-group-id")
    public void listen(String theMessage){
        System.out.println("Message received: "+ theMessage);
    }
}