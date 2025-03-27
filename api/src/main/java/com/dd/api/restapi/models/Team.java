package com.dd.api.restapi.models;

import com.dd.api.auth.models.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dd_teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    private String name;
    private long ghostedDate;

    @JsonCreator
    public Team(String name, User user) {
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return ghostedDate == team.ghostedDate && Objects.equals(id, team.id) && Objects.equals(user, team.user) && Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, ghostedDate);
    }

    @Override
    public String
    toString() {
        return "Team{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", ghostedDate=" + ghostedDate +
                '}';
    }
}