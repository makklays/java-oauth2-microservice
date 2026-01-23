package com.techmatrix18.service;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service layer for managing user-related operations.
 *
 * Provides methods for user registration, retrieval, update, and deletion.
 * Integrates with other components such as the OAuth2 Authorization Server
 * and API Gateway to handle authentication and user management.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registration a new User
     */
    public Mono<User> register(User user) {
        // Here you can add password hashing, validation, etc.
        return userRepository.save(user);
    }

    /**
     * Checks if a user with the given email already exists in the database.
     *
     * @param email
     * @return
     */
    public Mono<Boolean> existsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(u -> true)
                .defaultIfEmpty(false);
    }

    /**
     * Get User by ID
     */
    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Get all Users
     */
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Update data of User
     */
    public Mono<User> update(User user) {
        return userRepository.findById(user.getId())
            .flatMap(existingUser -> {
                existingUser.setEmail(user.getEmail());
                existingUser.setUsername(user.getUsername());
                // Update other fields as needed
                return userRepository.save(existingUser);
            });
    }

    /**
     * Delete user by ID
     */
    public Mono<Void> deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    /**
     * Authenticate user by username/email and password
     */
    public Mono<User> authenticate(String usernameOrEmail, String rawPassword) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .flatMap(user -> {
                if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                    return Mono.just(user);
                } else {
                    return Mono.empty(); // Invalid password
                }
            });
    }
}

