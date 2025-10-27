package com.example.backendapi.service;

import com.example.backendapi.exception.NotFoundException;
import com.example.backendapi.model.Note;
import com.example.backendapi.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for handling business logic related to Notes.
 */
@Service
@Transactional
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public Note create(Note note) {
        /** Create a new note. Title validation should be handled by controller validation layer. */
        return repository.save(note);
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public List<Note> findAll() {
        /** Retrieve all notes. */
        return repository.findAll();
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public Note findById(Long id) {
        /** Retrieve a note by id or throw NotFoundException. */
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
    }

    // PUBLIC_INTERFACE
    public Note update(Long id, Note updated) {
        /** Update a note by id. */
        Note existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setContent(updated.getContent());
        // updatedAt is automatically handled by @UpdateTimestamp on flush
        return repository.save(existing);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        /** Delete a note by id, throw if not found. */
        if (!repository.existsById(id)) {
            throw new NotFoundException("Note with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
