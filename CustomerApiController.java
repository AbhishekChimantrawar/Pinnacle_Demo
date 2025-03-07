package com.example.demo;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@RestController
@RequestMapping("/telco")
public class CustomerApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaProducerService kafkaProducerService;
    private static final String TOPIC_NAME = "user-topic";

    @GetMapping("/sendmsg")
    public ResponseEntity<String> sendMessage(@RequestParam String username,
                                              @RequestParam String password,
                                              @RequestParam Long mobile,
                                              @RequestParam String message) {
       
    	
    	System.err.println("tested    ok ");
    	
    	if (mobile.toString().length() != 10 || message.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("STATUS: REJECTED~~RESPONSE_CODE: FAILURE");
        }

        // Validate username and password
        Optional<User> user =  userService.validateUser(username, password);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("STATUS: REJECTED~~RESPONSE_CODE: FAILURE");
        }

        // Create acknowledgment ID
        String ackId = UUID.randomUUID().toString();

        // Produce message to Kafka
        MessagePayload payload = new MessagePayload(ackId, user.get().getAccountId(), mobile, message);
        kafkaProducerService.sendMessage(TOPIC_NAME,payload.toString());
// first step
        return ResponseEntity.ok("STATUS: ACCEPTED~~RESPONSE_CODE: SUCCESS~~" + ackId);
    }
}
