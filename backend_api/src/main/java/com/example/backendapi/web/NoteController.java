package com.example.backendapi.web;

import com.example.backendapi.model.Note;
import com.example.backendapi.service.NoteService;
import com.example.backendapi.web.dto.NoteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * REST controller for managing notes.
 */
@RestController
@RequestMapping("/api/notes")
@Validated
@Tag(name = "Notes", description = "CRUD operations for Notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(
            summary = "Create a note",
            description = "Creates a new note with a non-blank title.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = Note.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error",
                            content = @Content)
            }
    )
    public ResponseEntity<Note> create(@Valid @RequestBody NoteRequest request) {
        /** Create a new note. Returns 201 with Location header. */
        Note toSave = new Note(request.getTitle(), request.getContent());
        Note saved = service.create(toSave);
        return ResponseEntity.created(URI.create("/api/notes/" + saved.getId()))
                .body(saved);
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(summary = "List notes", description = "Returns all notes.")
    public ResponseEntity<List<Note>> list() {
        /** List all notes. */
        return ResponseEntity.ok(service.findAll());
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a note",
            description = "Returns the note with the specified id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = Note.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    public ResponseEntity<Note> get(@PathVariable Long id) {
        /** Get a note by id. */
        return ResponseEntity.ok(service.findById(id));
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a note",
            description = "Updates title and content of an existing note.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = Note.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    public ResponseEntity<Note> update(@PathVariable Long id, @Valid @RequestBody NoteRequest request) {
        /** Update a note by id. */
        Note updated = new Note(request.getTitle(), request.getContent());
        updated.setId(id);
        return ResponseEntity.ok(service.update(id, updated));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a note",
            description = "Deletes the note with the specified id.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        /** Delete a note by id. */
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
