package com.example.demo;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/internal-db")
public class InternalDbController {

    @Autowired
    
    private UserService userService;

    @Autowired
    private SendMessageRepository sendMessageRepository;

    // API to get Account ID by validating username and password
    @GetMapping
    ("/get-account-id")
    public ResponseEntity<Integer> getAccountId(@RequestParam String username,
                                                @RequestParam String password) {
        Optional<User> user = userService.validateUser(username, password);
        System.err.println("user exists"+user.toString());
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(user.get().getAccountId());
    }

    // API to insert send message data
    @PostMapping("/insert-send-msg")
    public ResponseEntity<Object> insertSendMsg(@RequestBody SendMessage sendMessage) {
        sendMessageRepository.save(sendMessage);
        return ResponseEntity.ok().body("message inserted suceesfully");
    }
}
