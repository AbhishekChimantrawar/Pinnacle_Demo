package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> validateUser(String username, String password) {
        // Find the user by username
        Optional<User> userOptional = userRepository.findByUsername(username);

        // If user exists, validate the password
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return userOptional;  // Return Optional containing the User
            }
        }

        // Return an empty Optional if user doesn't exist or password doesn't match
        return Optional.empty();
    }
}
