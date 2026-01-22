package com.techmatrix18.repository;

import com.techmatrix18.model.Token;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Token Repository
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@Repository
public interface TokenRepository extends ReactiveCrudRepository<Token, Long> {
    // TODO
}

