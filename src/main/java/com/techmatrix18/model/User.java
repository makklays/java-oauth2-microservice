package com.techmatrix18.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing a user in the system.
 *
 * Corresponds to the 'users' table in the database.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 21.01.2026
 */
@Table("users")
@Schema(description = "Entity representing a user")
public class User implements Serializable {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @Schema(description = "Unique identifier of the user", example = "123")
    private Long id;

    /**
     * username of a user
     */
    @NotNull
    @Size(min=2, max=30)
    @Schema(description = "User's name", example = "Alexander")
    private String username;

    /**
     * User email. Must be unique.
     */
    @NotNull
    @Email(message = "Invalid email address")
    @Schema(description = "User's email address", example = "user@example.com")
    private String email;

    /**
     * User mobile. Must be unique.
     */
    @NotNull
    @Column("mobile")
    @Schema(description = "User's mobile phone number", example = "+34 612 345 67")
    private String mobile;

    /**
     * User gender.
     */
    @Column("gender")
    @Schema(description = "User's gender", example = "male")
    private String gender;

    /**
     * User age.
     */
    @Column("age")
    @Schema(description = "User's age", example = "23")
    private String age;

    /**
     * User avatar.
     */
    @Column("avatar")
    @Schema(description = "User's avatar filename or URL", example = "avatar.png")
    private String avatar;

    /**
     * The user's hashed password.
     */
    @NotNull
    @Column("password")
    @Schema(description = "User's password", example = "love")
    private String password;

    /**
     * Date and time of user creation.
     */
    @CreationTimestamp
    @Column("created_at")
    @Schema(description = "User creation date", example = "2025-02-19 00:00:00")
    private Instant createdAt;

    /**
     * Date and time of user update.
     */
    @UpdateTimestamp
    @Column("updated_at")
    @Schema(description = "User last update timestamp", example = "2025-02-19 00:00:00")
    private Instant updatedAt;

    // getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId().equals(user.getId()) && getUsername().equals(user.getUsername()) &&
                getEmail().equals(user.getEmail()) && getMobile().equals(user.getMobile()) &&
                getGender().equals(user.getGender()) && getAge().equals(user.getAge()) &&
                getAvatar().equals(user.getAvatar()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "User {" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", gender='" + gender + '\'' +
            ", age='" + age + '\'' +
            ", avatar='" + avatar + '\'' +
            ", password='" + password + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}

