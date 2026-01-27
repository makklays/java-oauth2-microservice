package com.techmatrix18.repository;

import com.techmatrix18.enums.Role;
import com.techmatrix18.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * User Repository
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 27.01.2026
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Mono<User> findByUsernameOrEmail(String username, String email);

    Mono<User> findByEmail(String email);

    @Query("SELECT roles FROM users WHERE id = :userId")
    Mono<Set<Role>> findRolesByUserId(Long userId);
}

