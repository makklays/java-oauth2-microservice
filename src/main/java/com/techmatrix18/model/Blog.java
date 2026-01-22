package com.techmatrix18.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * An entity representing a blog in the system.
 *
 * Corresponds to the 'blogs' table in the database.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 21.01.2026
 */
@Table("blogs")
public class Blog {

    @Id
    @Schema(description = "Unique identifier of the blog post", example = "1")
    private Long id;

    @Column("user_id")
    @Schema(description = "ID of the user who created the blog post", example = "123")
    private Long userId;

    @Column("title")
    @Schema(description = "Title of the blog post", example = "My First Blog")
    private String title;

    @Column("post")
    @Schema(description = "Content of the blog post", example = "This is the body of my first blog post...")
    private String post;

    @CreationTimestamp
    @Column("created_at")
    @Schema(description = "Timestamp when the blog post was created", example = "2025-02-19T00:00:00Z")
    private Instant createdAt;

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

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blog blog)) return false;
        return getId().equals(blog.getId()) && getUserId().equals(blog.getUserId()) &&
                getTitle().equals(blog.getTitle()) && getPost().equals(blog.getPost());
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Blog {" +
            "id=" + id +
            ", userId=" + userId +
            ", title='" + title + '\'' +
            ", post='" + post + '\'' +
            ", createdAt=" + createdAt +
            '}';
    }
}

