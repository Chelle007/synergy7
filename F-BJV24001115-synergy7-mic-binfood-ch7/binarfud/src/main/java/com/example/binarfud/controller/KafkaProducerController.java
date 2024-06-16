package com.example.binarfud.controller;

import com.example.binarfud.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage("notification-topic", message);
        return "Message sent to Kafka topic";
    }
}