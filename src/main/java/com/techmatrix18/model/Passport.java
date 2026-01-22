package com.techmatrix18.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

/**
 * An entity representing a passport in the system.
 *
 * Corresponds to the 'passports' table in the database.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 21.01.2026
 */
@Table(name = "passports")
public class Passport {

    @Id
    @Schema(description = "Unique identifier of the passport", example = "1")
    private Long id;

    @Column("user_id")
    @Schema(description = "ID of the user who owns this passport", example = "123")
    private Long userId;

    @Column("number")
    @Schema(description = "Passport number", example = "AB1234567")
    private String number;

    @Column("date_at")
    @Schema(description = "Date and time when the passport was issued", example = "2020-05-15T10:30:00Z")
    private Instant dateAt;

    @Column("who_gave_it")
    @Schema(description = "Authority or person who issued the passport", example = "Ministry of Internal Affairs")
    private String whoGaveIt;

    @CreationTimestamp
    @Column("created_at")
    @Schema(description = "Timestamp when the record was created in the database", example = "2025-02-19T00:00:00Z")
    private Instant createdAt;

    @UpdateTimestamp
    @Column("updated_at")
    @Schema(description = "Timestamp when the record was last updated in the database", example = "2025-02-19T12:45:00Z")
    private Instant updatedAt;

    // getters and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Instant getDateAt() {
        return dateAt;
    }
    public void setDateAt(Instant dateAt) {
        this.dateAt = dateAt;
    }

    public String getWhoGaveIt() {
        return whoGaveIt;
    }
    public void setWhoGaveIt(String whoGaveIt) {
        this.whoGaveIt = whoGaveIt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport passport)) return false;
        return getId().equals(passport.getId()) && getUserId().equals(passport.getUserId()) &&
                getNumber().equals(passport.getNumber()) && getDateAt().equals(passport.getDateAt()) &&
                getWhoGaveIt().equals(passport.getWhoGaveIt());
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getDateAt() != null ? getDateAt().hashCode() : 0);
        result = 31 * result + (getWhoGaveIt() != null ? getWhoGaveIt().hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Passport {" +
            "id=" + id +
            ", userId=" + userId +
            ", number='" + number + '\'' +
            ", dateAt=" + dateAt +
            ", whoGaveIt='" + whoGaveIt + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}

