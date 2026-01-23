package com.techmatrix18.repository;

import com.techmatrix18.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * User Repository
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Mono<User> findByUsernameOrEmail(String username, String email);

    Mono<User> findByEmail(String email);
}

