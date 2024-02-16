package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dd_teams", schema = "sp24")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private long ghostedDate;

    public Team(Long id, String name, long ghostedDate) {
        this.id = id;
        this.name = name;
        this.ghostedDate = ghostedDate;
    }

    public Team(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    @JsonCreator
    public Team(String name) {
        this.name = name;
        this.ghostedDate = 0;
    }

    public Team() {
        // Empty for Hibernate
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGhostedDate() {
        return ghostedDate;
    }

    public void setGhostedDate(long ghostedDate) {
        this.ghostedDate = ghostedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}