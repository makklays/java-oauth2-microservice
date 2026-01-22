package com.techmatrix18.repository;

import com.techmatrix18.model.Passport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Passport Repository
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@Repository
public interface PassportRepository extends ReactiveCrudRepository<Passport, Long> {
    // TODO
}

