package com.techmatrix18.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity representing a user token for access management.
 * Contains access token, refresh token, and password reset token with their expiration times.
 * Useful for authentication, session management, and password recovery.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@Table("tokens")
public class Token {

    @Id
    @Schema(description = "Unique identifier of the token", example = "1")
    private Long id;

    @Column("user_id")
    private Long userId;

    // Access token
    @Column("token")
    @Schema(description = "Access token value")
    private String token;

    @Column("expired_token")
    @Schema(description = "Expiration time of the token", example = "2026-01-22T23:59:59")
    private Instant expiredToken;

    // Refresh token
    @Column("refresh_token")
    @Schema(description = "Refresh token value")
    private String refreshToken;

    @Column("expired_refresh_token")
    @Schema(description = "Expiration time of the refresh token", example = "2026-02-22T23:59:59")
    private Instant expiredRefreshToken;

    // password reset Token
    @Column("password_reset_token")
    @Schema(description = "Password reset token value")
    private String passwordResetToken;

    @Column("expired_password_reset_token")
    @Schema(description = "Expiration time of the password reset token", example = "2026-02-22T23:59:59")
    private Instant expiredPasswordResetToken;

    //
    @Column("ip_address")
    @Schema(description = "IP address from which token was created")
    private String ipAddress;

    @Column("user_agent")
    @Schema(description = "User-Agent string of client")
    private String userAgent;

    @Column("revoked")
    @Schema(description = "Indicates if token was revoked manually or due to logout")
    private Boolean revoked;

    @Column("created_at")
    @Schema(description = "Time of token creation")
    private Instant createdAt;

    @Column("updated_at")
    @Schema(description = "Time of last update")
    private Instant updatedAt;

    // getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Instant getExpiredToken() { return expiredToken; }
    public void setExpiredToken(Instant expiredToken) { this.expiredToken = expiredToken; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public Instant getExpiredRefreshToken() { return expiredRefreshToken; }
    public void setExpiredRefreshToken(Instant expiredRefreshToken) { this.expiredRefreshToken = expiredRefreshToken; }

    public String getPasswordResetToken() { return passwordResetToken; }
    public void setPasswordResetToken(String passwordResetToken) { this.passwordResetToken = passwordResetToken; }

    public Instant getExpiredPasswordResetToken() { return expiredPasswordResetToken; }
    public void setExpiredPasswordResetToken(Instant expiredPasswordResetToken) { this.expiredPasswordResetToken = expiredPasswordResetToken; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }

    public Boolean getRevoked() { return revoked; }
    public void setRevoked(Boolean revoked) { this.revoked = revoked; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token other)) return false;

        if (!getId().equals(other.getId())) return false;
        if (!getUserId().equals(other.getUserId())) return false;
        if (!getToken().equals(other.getToken())) return false;
        if (!getExpiredToken().equals(other.getExpiredToken())) return false;
        if (!getRefreshToken().equals(other.getRefreshToken())) return false;
        if (!getExpiredRefreshToken().equals(other.getExpiredRefreshToken())) return false;
        if (!getPasswordResetToken().equals(other.getPasswordResetToken())) return false;
        if (!getExpiredPasswordResetToken().equals(other.getExpiredPasswordResetToken())) return false;
        if (!getRevoked().equals(other.getRevoked())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + getId().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + getToken().hashCode();
        result = 31 * result + getExpiredToken().hashCode();
        result = 31 * result + getRefreshToken().hashCode();
        result = 31 * result + getExpiredRefreshToken().hashCode();
        result = 31 * result + getPasswordResetToken().hashCode();
        result = 31 * result + getExpiredPasswordResetToken().hashCode();
        result = 31 * result + getRevoked().hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Token {" +
            "id=" + id +
            ", userId=" + userId +
            ", token='" + token + '\'' +
            ", expiredToken=" + expiredToken +
            ", refreshToken='" + refreshToken + '\'' +
            ", expiredRefreshToken=" + expiredRefreshToken +
            ", passwordResetToken='" + passwordResetToken + '\'' +
            ", expiredPasswordResetToken=" + expiredPasswordResetToken +
            ", ipAddress='" + ipAddress + '\'' +
            ", userAgent='" + userAgent + '\'' +
            ", revoked=" + revoked +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}

