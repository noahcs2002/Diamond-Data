package com.dd.api.restapi.services;

import com.dd.api.auth.models.User;
import com.dd.api.auth.providers.AuthorizationService;
import com.dd.api.restapi.models.Note;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.NoteRepository;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NoteService {

    @Autowired
    private final NoteRepository repository;


    @Autowired
    private final AuthorizationService authorizationService;

    @Autowired
    private final TeamService teamService;

    @Autowired
    public NoteService(NoteRepository repository, AuthorizationService authorizationService, TeamService teamService) {
        this.repository = repository;
        this.authorizationService = authorizationService;
        this.teamService = teamService;
    }

    @Transactional
    public Note getNote(Long id) {
        Objects.requireNonNull(id);
        return this.repository.findAll()
                .stream()
                .filter(n -> n.getId().equals(id))
                .filter(note -> note.getGhostedDate() == 0)
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public List<Note> getNotesForTeam(Long teamId) {
        Objects.requireNonNull(teamId);

        return this.repository.findAll()
                .stream()
                .filter(note -> note.getTeam().getId().equals(teamId))
                .filter(note -> note.getGhostedDate() == 0)
                .toList();
    }

    @Transactional
    public Note createNote(Note note, Long teamId, Long userId) {
        User user = this.authorizationService.getUser(userId);
        Team team = this.teamService.getTeamById(teamId);
        team.setUser(user);
        note.setTeam(team);
        return this.repository.save(Objects.requireNonNull(note));
    }

    @Transactional
    public boolean deleteNote(Long id) {
        Note note = this.getNote(id);
        note.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
        this.repository.save(note);
        return true;
    }

    @Transactional
    public Note updateNote(Long id, String newNoteText) {
        Note note = this.getNote(id);
        note.setBody(newNoteText);
        return this.repository.save(note);
    }
}
