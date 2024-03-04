package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name="dd_players", schema="sp24")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="offensive_player_id", referencedColumnName = "id")
    private OffensivePlayer offensivePlayer;

    @ManyToOne()
    @JoinColumn(name="defensive_player_id", referencedColumnName = "id")
    private DefensivePlayer defensivePlayer;

    private String firstName;
    private String lastName;

    private long ghostedDate;

    public Player(Long id,
                  OffensivePlayer offensivePlayer,
                  DefensivePlayer defensivePlayer,
                  long ghostedDate) {
        this.id = id;
        this.offensivePlayer = offensivePlayer;
        this.defensivePlayer = defensivePlayer;
        this.ghostedDate = ghostedDate;
    }

    @JsonCreator
    public Player(OffensivePlayer offensivePlayer, DefensivePlayer defensivePlayer, String firstName, String lastName) {
        this.offensivePlayer = offensivePlayer;
        this.defensivePlayer = defensivePlayer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ghostedDate = 0;
    }

    /**
     * Composite model for joining Offensive Players
     * and Defensive Players into one cohesive model
     */
    public Player() {
        // Hibernate Constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffensivePlayer getOffensivePlayer() {
        return offensivePlayer;
    }

    public void setOffensivePlayer(OffensivePlayer offensivePlayer) {
        this.offensivePlayer = offensivePlayer;
    }

    public DefensivePlayer getDefensivePlayer() {
        return defensivePlayer;
    }

    public void setDefensivePlayer(DefensivePlayer defensivePlayer) {
        this.defensivePlayer = defensivePlayer;
    }

    public long getGhostedDate() {
        return ghostedDate;
    }

    public void setGhostedDate(long ghostedDate) {
        this.ghostedDate = ghostedDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return ghostedDate == player.ghostedDate && Objects.equals(id, player.id) && Objects.equals(offensivePlayer, player.offensivePlayer) && Objects.equals(defensivePlayer, player.defensivePlayer) && Objects.equals(firstName, player.firstName) && Objects.equals(lastName, player.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offensivePlayer, defensivePlayer, firstName, lastName, ghostedDate);
    }
}