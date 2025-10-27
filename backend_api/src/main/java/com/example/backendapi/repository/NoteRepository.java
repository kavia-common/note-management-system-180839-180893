package com.example.backendapi.repository;

import com.example.backendapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Note entities.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
