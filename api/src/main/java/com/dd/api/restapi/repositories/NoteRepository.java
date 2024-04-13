package com.dd.api.restapi.repositories;

import com.dd.api.restapi.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
