package com.techmatrix18.service;

import com.techmatrix18.dto.TokenResponseDto;
import com.techmatrix18.model.Token;
import com.techmatrix18.repository.TokenRepository;
import com.techmatrix18.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

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

    // methods

    /**
     * Generates access and refresh tokens for a given user, saves them in the database,
     * and returns a DTO containing the tokens and their expiration time.
     *
     * @param userId
     * @param ipAddress
     * @param userAgent
     * @return
     */
    public Mono<TokenResponseDto> generateTokens(Long userId, String ipAddress, String userAgent) {
        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plus(1, ChronoUnit.HOURS);

        Token token = new Token();
        token.setUserId(userId);
        token.setToken(accessToken);
        token.setExpiredToken(expiresAt);
        token.setRefreshToken(refreshToken);
        token.setExpiredRefreshToken(expiresAt.plus(30, ChronoUnit.DAYS));
        token.setIpAddress(ipAddress);
        token.setUserAgent(userAgent);
        token.setRevoked(false);
        token.setCreatedAt(Instant.now());
        token.setUpdatedAt(Instant.now());

        // Save the token reactively
        return tokenRepository.save(token) // save return Mono<Token>
                .map(saved -> new TokenResponseDto(accessToken, refreshToken, expiresAt));
    }

    /**
     * Refresh tokens using refreshToken value
     *
     * @param refreshToken
     * @param ipAddress
     * @param userAgent
     * @return
     */
    public Mono<TokenResponseDto> refreshToken(String refreshToken, String ipAddress, String userAgent) {
        Instant now = Instant.now();

        return tokenRepository.findByRefreshTokenAndRevokedFalse(refreshToken)
            .filter(token -> token.getExpiredRefreshToken().isAfter(now))
            .flatMap(oldToken -> {
                // Generate new tokens
                String newAccessToken = UUID.randomUUID().toString();
                String newRefreshToken = UUID.randomUUID().toString();
                Instant newAccessExpiry = now.plus(1, ChronoUnit.HOURS);
                Instant newRefreshExpiry = now.plus(30, ChronoUnit.DAYS);

                oldToken.setToken(newAccessToken);
                oldToken.setExpiredToken(newAccessExpiry);
                oldToken.setRefreshToken(newRefreshToken);
                oldToken.setExpiredRefreshToken(newRefreshExpiry);
                oldToken.setIpAddress(ipAddress);
                oldToken.setUserAgent(userAgent);
                oldToken.setUpdatedAt(now);

                return tokenRepository.save(oldToken)
                    .map(saved -> new TokenResponseDto(newAccessToken, newRefreshToken, newAccessExpiry));
            });
    }
}

