package com.example.finallearing.config;

import com.example.finallearing.entity.User;
import com.example.finallearing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            log.info("Initializing database with random users...");

            // Create random users
            createUser("admin", "admin123", "admin@example.com", "Admin User", "ADMIN");
            createUser("john", "password123", "john@example.com", "John Doe", "USER");
            createUser("jane", "password123", "jane@example.com", "Jane Smith", "USER");
            createUser("bob", "password123", "bob@example.com", "Bob Johnson", "USER");
            createUser("alice", "password123", "alice@example.com", "Alice Williams", "USER");

            log.info("Database initialized with {} users", userRepository.count());
        } else {
            log.info("Database already contains {} users", userRepository.count());
        }
    }

    private void createUser(String username, String password, String email, String fullName, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setRole(role);
        user.setEnabled(true);
        userRepository.save(user);
        log.info("Created user: {}", username);
    }
}

