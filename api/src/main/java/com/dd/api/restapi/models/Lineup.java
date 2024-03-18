package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
@Table(name="dd_lineups", schema="sp24")
public class Lineup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name="first_base_id", referencedColumnName = "id")
    private DefensivePlayer firstBase;

    @ManyToOne
    @JoinColumn(name="second_base_id", referencedColumnName = "id")
    private DefensivePlayer secondBase;

    @ManyToOne
    @JoinColumn(name="third_base_id", referencedColumnName = "id")
    private DefensivePlayer thirdBase;

    @ManyToOne
    @JoinColumn(name="shortstop_id", referencedColumnName = "id")
    private DefensivePlayer shortstop;

    @ManyToOne
    @JoinColumn(name="left_field_id", referencedColumnName = "id")
    private DefensivePlayer leftField;

    @ManyToOne
    @JoinColumn(name="right_field_id", referencedColumnName = "id")
    private DefensivePlayer rightField;

    @ManyToOne
    @JoinColumn(name="center_field_id", referencedColumnName = "id")
    private DefensivePlayer centerField;

    @ManyToOne
    @JoinColumn(name = "catcher_id", referencedColumnName = "id")
    private DefensivePlayer catcher;

    @ManyToOne
    @JoinColumn(name = "", referencedColumnName = "")
    private Pitcher pitcher;

    private long ghostedDate;

    public Lineup () {
        // Empty for hibernate
    }

    @JsonCreator
    public Lineup(Team team,
                  DefensivePlayer firstBase,
                  DefensivePlayer secondBase,
                  DefensivePlayer thirdBase,
                  DefensivePlayer shortstop,
                  DefensivePlayer leftField,
                  DefensivePlayer rightField,
                  DefensivePlayer centerField,
                  DefensivePlayer catcher,
                  Pitcher pitcher) {
        this.team = team;
        this.firstBase = firstBase;
        this.secondBase = secondBase;
        this.thirdBase = thirdBase;
        this.shortstop = shortstop;
        this.leftField = leftField;
        this.rightField = rightField;
        this.centerField = centerField;
        this.catcher = catcher;
        this.pitcher = pitcher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public DefensivePlayer getFirstBase() {
        return firstBase;
    }

    public void setFirstBase(DefensivePlayer firstBase) {
        this.firstBase = firstBase;
    }

    public DefensivePlayer getSecondBase() {
        return secondBase;
    }

    public void setSecondBase(DefensivePlayer secondBase) {
        this.secondBase = secondBase;
    }

    public DefensivePlayer getThirdBase() {
        return thirdBase;
    }

    public void setThirdBase(DefensivePlayer thirdBase) {
        this.thirdBase = thirdBase;
    }

    public DefensivePlayer getShortstop() {
        return shortstop;
    }

    public void setShortstop(DefensivePlayer shortstop) {
        this.shortstop = shortstop;
    }

    public DefensivePlayer getLeftField() {
        return leftField;
    }

    public void setLeftField(DefensivePlayer leftField) {
        this.leftField = leftField;
    }

    public DefensivePlayer getRightField() {
        return rightField;
    }

    public void setRightField(DefensivePlayer rightField) {
        this.rightField = rightField;
    }

    public DefensivePlayer getCenterField() {
        return centerField;
    }

    public void setCenterField(DefensivePlayer centerField) {
        this.centerField = centerField;
    }

    public DefensivePlayer getCatcher() {
        return catcher;
    }

    public void setCatcher(DefensivePlayer catcher) {
        this.catcher = catcher;
    }

    public Pitcher getPitcher() {
        return pitcher;
    }

    public void setPitcher(Pitcher pitcher) {
        this.pitcher = pitcher;
    }

    public long getGhostedDate() {
        return ghostedDate;
    }

    public void setGhostedDate(long ghostedDate) {
        this.ghostedDate = ghostedDate;
    }
}
