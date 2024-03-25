package com.dd.api.auth.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dd_users", schema = "sp24")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;

    @Column(name = "password_hash")
    private String password;
    private long ghostedDate;

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.ghostedDate = 0;
    }

    @JsonCreator
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.ghostedDate = 0;
    }

    public User() {
        // Empty for Hibernate
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    public boolean transientEqualityCheck(User user) {
        if (this == user) return true;
        if (user == null || getClass() != user.getClass()) return false;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, ghostedDate);
    }
}
