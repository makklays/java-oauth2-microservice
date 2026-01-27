package com.techmatrix18.service;

import com.techmatrix18.security.JwtKeyProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * Service layer for managing JWT token-related operations.
 * Provides methods for generating JWT access tokens.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 27.01.2026
 */
@Service
public class JwtTokenService {

    private final JwtKeyProvider keyProvider;

    public JwtTokenService(JwtKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    public String generateAccessToken(Long userId, List<String> roles) {
        Instant now = Instant.now();

        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("roles", roles)           // <-- here the array of roles is placed
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(1, ChronoUnit.HOURS)))
            .signWith(keyProvider.getPrivateKey(), SignatureAlgorithm.RS256) // <-- signature
            .compact();
    }
}

