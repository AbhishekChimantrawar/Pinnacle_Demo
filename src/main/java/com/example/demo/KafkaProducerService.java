package com.example.demo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // Constructor injection of KafkaTemplate
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to send message
    public void sendMessage(String topic, String message) {
        // Send message asynchronously and return a CompletableFuture
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        // Handle the result of the CompletableFuture
        future.thenAccept(result -> {
            // Success callback
            if (result != null) {
                System.out.println("Message sent successfully: " + message);
            }
        }).exceptionally(ex -> {
            // Error callback
            System.err.println("Error sending message: " + ex.getMessage());
            return null;
        });
    }
}
