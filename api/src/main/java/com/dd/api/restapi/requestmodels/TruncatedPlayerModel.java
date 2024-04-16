package com.dd.api.restapi.requestmodels;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class TruncatedPlayerModel {
    private String name;
    private Long id;
    private String assignment;
    private String position;

    @JsonCreator
    public TruncatedPlayerModel(String name, Long id, String assignment, String position) {
        this.name = name;
        this.id = id;
        this.assignment = assignment;
        this.position = position;
    }

    public TruncatedPlayerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruncatedPlayerModel that = (TruncatedPlayerModel) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id) && Objects.equals(assignment, that.assignment) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, assignment, position);
    }

    @Override
    public String toString() {
        return "TruncatedPlayerModel{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", assignment='" + assignment + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
