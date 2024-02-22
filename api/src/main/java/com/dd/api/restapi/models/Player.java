package com.dd.api.restapi.models;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name="dd_players", schema="sp24")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="offensive_player_id", referencedColumnName = "id")
    private OffensivePlayer offensivePlayer;

    @ManyToOne
    @JoinColumn(name="defensive_player_id", referencedColumnName = "id")
    private DefensivePlayer defensivePlayer;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return ghostedDate == player.ghostedDate && Objects.equals(id, player.id) && Objects.equals(offensivePlayer, player.offensivePlayer) && Objects.equals(defensivePlayer, player.defensivePlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offensivePlayer, defensivePlayer, ghostedDate);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", offensivePlayer=" + offensivePlayer +
                ", defensivePlayer=" + defensivePlayer +
                ", ghostedDate=" + ghostedDate +
                '}';
    }
}