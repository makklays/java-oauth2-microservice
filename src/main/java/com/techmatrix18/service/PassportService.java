package com.techmatrix18.service;

import com.techmatrix18.repository.PassportRepository;
import com.techmatrix18.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Passport Service
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@Service
public class PassportService {
    private final PassportRepository passportRepository;
    private final UserRepository userRepository;

    public PassportService(UserRepository userRepository, PassportRepository passportRepository) {
        this.userRepository = userRepository;
        this.passportRepository = passportRepository;
    }

    //
}

