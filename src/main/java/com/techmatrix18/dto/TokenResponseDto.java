package com.techmatrix18.dto;

import java.time.Instant;

/**
 * Token Response
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 21.01.2026
 *
 * @param accessToken
 * @param refreshToken
 * @param expiresAt
 */
public record TokenResponseDto (
    String accessToken,
    String refreshToken,
    Instant expiresAt
) {}

