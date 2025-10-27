package com.example.backendapi.web.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request payload for creating/updating a Note.
 */
public class NoteRequest {

    @NotBlank(message = "title must not be blank")
    private String title;

    private String content;

    public NoteRequest() {}

    public NoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // PUBLIC_INTERFACE
    public String getTitle() {
        /** Get note title from request. */
        return title;
    }

    // PUBLIC_INTERFACE
    public void setTitle(String title) {
        /** Set note title in request. */
        this.title = title;
    }

    // PUBLIC_INTERFACE
    public String getContent() {
        /** Get note content from request. */
        return content;
    }

    // PUBLIC_INTERFACE
    public void setContent(String content) {
        /** Set note content in request. */
        this.content = content;
    }
}
