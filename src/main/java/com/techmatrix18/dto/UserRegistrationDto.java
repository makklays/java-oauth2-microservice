package com.techmatrix18.dto;

/**
 * User Registration
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 21.01.2026
 *
 * @param username
 * @param email
 * @param password
 * @param mobile
 * @param gender
 * @param age
 */
public record UserRegistrationDto(String username, String email, String password, String mobile, String gender, Integer age) {}

