package com.example.demo;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telco")
public class TelecomOperatorController {

    @PostMapping("/smsc")
    public ResponseEntity<String> sendSmsToOperator(@RequestParam Integer accountId,
                                                     @RequestParam Long mobile,
                                                     @RequestParam String message) {
        if (mobile.toString().length() != 10 || message.isEmpty() || accountId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("STATUS: REJECTED~~RESPONSE_CODE: FAILURE");
        }

        // Simulate SMS sending to Telecom Operator
        String ackId = UUID.randomUUID().toString();
        return ResponseEntity.ok("STATUS: ACCEPTED~~RESPONSE_CODE: SUCCESS~~" + ackId);
    }
}
