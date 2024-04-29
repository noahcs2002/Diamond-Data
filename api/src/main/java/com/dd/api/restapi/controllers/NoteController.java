package com.dd.api.restapi.controllers;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.models.Note;
import com.dd.api.restapi.services.NoteService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/diamond-data/api/notes")
public class NoteController {

    @Autowired
    private final Validator validator;

    @Autowired
    private final NoteService noteService;

    public NoteController(Validator validator, NoteService noteService) {
        this.validator = validator;
        this.noteService = noteService;
    }

    @PostMapping
    @RequestMapping("/create")
    public Note createNote(@RequestBody Note note, @RequestParam Long userId, @RequestParam Long teamId) throws NoAccessPermittedException {
        Objects.requireNonNull(note);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(teamId);

        if(!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.noteService.createNote(note, teamId, userId);
    }

    @GetMapping
    @RequestMapping("/get")
    public Note getNote(@RequestParam Long id, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(id);

        if (!this.validator.validateNote(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.noteService.getNote(id);
    }

    @GetMapping
    @RequestMapping("/get-by-team")
    public List<Note> getByTeam(@RequestParam Long teamId, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(teamId);
        Objects.requireNonNull(userId);

        if(!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.noteService.getNotesForTeam(teamId);
    }

    @PutMapping
    @RequestMapping("/update")
    public Note updateNote(@RequestParam Long id, @RequestParam Long userId, @RequestParam String newNoteText) throws NoAccessPermittedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(newNoteText);

        if(!this.validator.validateNote(userId, id)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.noteService.updateNote(id, newNoteText);
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public boolean deleteNote(@RequestParam Long noteId, @RequestParam Long userId) throws NoAccessPermittedException {
        Objects.requireNonNull(noteId);
        Objects.requireNonNull(userId);

        if(!this.validator.validateNote(userId, noteId)) {
            throw new NoAccessPermittedException(userId);
        }

        return this.noteService.deleteNote(noteId);
    }
}