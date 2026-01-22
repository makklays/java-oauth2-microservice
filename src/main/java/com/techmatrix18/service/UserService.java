package com.techmatrix18.service;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
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
 * @since 21.01.2026
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Регистрация нового пользователя.
     */
    public Mono<User> register(User user) {
        // Здесь можно добавить хеширование пароля, валидацию и т.д.
        return userRepository.save(user);
    }

    /**
     * Получить пользователя по ID.
     */
    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Получить всех пользователей.
     */
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Обновить данные пользователя.
     */
    public Mono<User> update(User user) {
        return userRepository.findById(user.getId())
                .flatMap(existingUser -> {
                    existingUser.setEmail(user.getEmail());
                    existingUser.setUsername(user.getUsername());
                    // обновить другие поля по необходимости
                    return userRepository.save(existingUser);
                });
    }

    /**
     * Удалить пользователя по ID.
     */
    public Mono<Void> deleteById(Long id) {
        return userRepository.deleteById(id);
    }
}

