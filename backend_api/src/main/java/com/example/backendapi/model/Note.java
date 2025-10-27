package com.example.backendapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

/**
 * Note entity representing a note record.
 */
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title must not be blank")
    @Column(nullable = false, length = 255)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    public Note() {}

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    // PUBLIC_INTERFACE
    public void setId(Long id) {
        /** Set the note id (usually managed by JPA). */
        this.id = id;
    }

    // PUBLIC_INTERFACE
    public String getTitle() {
        /** Get the note title. */
        return title;
    }

    // PUBLIC_INTERFACE
    public void setTitle(String title) {
        /** Set the note title. */
        this.title = title;
    }

    // PUBLIC_INTERFACE
    public String getContent() {
        /** Get the note content. */
        return content;
    }

    // PUBLIC_INTERFACE
    public void setContent(String content) {
        /** Set the note content. */
        this.content = content;
    }

    // PUBLIC_INTERFACE
    public OffsetDateTime getCreatedAt() {
        /** Get the note creation timestamp. */
        return createdAt;
    }

    // PUBLIC_INTERFACE
    public OffsetDateTime getUpdatedAt() {
        /** Get the note last update timestamp. */
        return updatedAt;
    }
}
