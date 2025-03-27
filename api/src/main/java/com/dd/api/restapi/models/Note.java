package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="dd_notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String body;
    @ManyToOne
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;

    private long ghostedDate;

    @JsonCreator
    public Note(String body, Team team) {
        this.body = body;
        this.team = team;
        this.ghostedDate = 0;
    }

    public Note() {
        // Empty for Hibernate
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public long getGhostedDate() {
        return ghostedDate;
    }

    public void setGhostedDate(long ghostedDate) {
        this.ghostedDate = ghostedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return ghostedDate == note.ghostedDate && Objects.equals(id, note.id) && Objects.equals(body, note.body) && Objects.equals(team, note.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, team, ghostedDate);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", team=" + team +
                ", ghostedDate=" + ghostedDate +
                '}';
    }
}