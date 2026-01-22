package com.techmatrix18.service;

import com.techmatrix18.repository.TokenRepository;
import com.techmatrix18.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Service layer for managing token-related operations.
 *
 * Provides methods for token.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public TokenService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    //
}

