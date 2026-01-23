package com.techmatrix18.dto;

/**
 * Login Request
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 21.01.2026
 *
 * @param username
 * @param password
 */
public record LoginRequestDto (String username, String password) {}

