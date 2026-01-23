package com.techmatrix18.controller;

import com.techmatrix18.dto.UserRegistrationDto;
import org.springframework.http.server.reactive.ServerHttpRequest;
import com.techmatrix18.dto.LoginRequestDto;
import com.techmatrix18.dto.TokenResponseDto;
import com.techmatrix18.model.User;
import com.techmatrix18.service.TokenService;
import com.techmatrix18.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing users.
 * Exposes endpoints for retrieving and manipulating user data.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@RestController
@RequestMapping("/api/v1/oauth2")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user and returns access and refresh tokens.
     *
     * @param request   user registration data (username, email, password, etc.)
     * @param httpRequest HTTP request (to extract IP and User-Agent)
     * @return a Mono emitting ResponseEntity with TokenResponseDto if successful, or BAD_REQUEST
     */
    @PostMapping("/registration")
    public Mono<ResponseEntity<TokenResponseDto>> registration(@RequestBody UserRegistrationDto request,
                                                               ServerHttpRequest httpRequest) {
        // Create a new User from DTO
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password())); // Hash the password
        user.setMobile(request.mobile());
        user.setGender(request.gender());
        user.setAge(request.age() != null ? request.age().toString() : null);

        return userService.register(user) // return Mono<User>
            .flatMap(savedUser -> {
                // Get IP address and User-Agent
                String ip = httpRequest.getRemoteAddress() != null
                    ? httpRequest.getRemoteAddress().getAddress().getHostAddress()
                    : "unknown";
                String userAgent = httpRequest.getHeaders().getFirst("User-Agent");

                // Generate tokens and save them in the tokens table
                return tokenService.generateTokens(savedUser.getId(), ip, userAgent);
            })
            .map(tokenResponse -> ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse))
            .onErrorResume(e -> Mono.just(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
            ));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<TokenResponseDto>> login(@RequestBody LoginRequestDto request, ServerHttpRequest httpRequest) {
        return userService.authenticate(request.username(), request.password())
            .flatMap(user -> {
                String ip = httpRequest.getRemoteAddress() != null
                    ? httpRequest.getRemoteAddress().getAddress().getHostAddress()
                    : "unknown";
                String userAgent = httpRequest.getHeaders().getFirst("User-Agent");

                // return Mono<TokenResponseDto>
                return tokenService.generateTokens(user.getId(), ip, userAgent);
            })
            .map(tokenResponse -> ResponseEntity.ok(tokenResponse))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/refresh-token")
    public Mono<ResponseEntity<TokenResponseDto>> refreshToken(@RequestParam String refreshToken, ServerHttpRequest httpRequest) {
        String ip = httpRequest.getRemoteAddress() != null
            ? httpRequest.getRemoteAddress().getAddress().getHostAddress()
            : "unknown";
        String userAgent = httpRequest.getHeaders().getFirst("User-Agent");

        return tokenService.refreshToken(refreshToken, ip, userAgent)
            .map(tokenResponse -> ResponseEntity.ok(tokenResponse))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}

