package com.example.demo;



import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@KafkaListener(topics = "sms-topic", groupId = "sms-group")
public class KafkaConsumerService {

    @Autowired
    
    private SendMessageRepository sendMessageRepository;

    @Transactional
    public void consumeMessage(MessagePayload payload) {
        SendMessage sendMessage = new SendMessage(payload.getMobile(), payload.getMessage(),
                                                  "NEW", new Timestamp(System.currentTimeMillis()), payload.getAccountId());
        sendMessageRepository.save(sendMessage);
    }
}
