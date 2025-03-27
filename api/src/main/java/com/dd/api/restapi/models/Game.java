package com.dd.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity()
@Table(name = "dd_games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    private Date gameDate;
    private int teamScore;
    private int opponentScore;
    private char verdict;
    private Long ghostedDate;

    @JsonCreator
    public Game(Team team,
                SqlDateWrapper gameDate,
                int teamScore,
                int opponentScore) {
        this.team = team;
        this.gameDate = gameDate.getSqlDate();
        this.teamScore = teamScore;
        this.opponentScore = opponentScore;
        this.verdict = teamScore > opponentScore ? 'W' : 'L';
        this.ghostedDate = 0L;
    }

    public Game() {
        // Empty for hibernate
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Date getGameDate() {
        return this.gameDate;
    }

    public void setGameDate(SqlDateWrapper gameSqlDateWrapper) {
        this.gameDate = gameSqlDateWrapper.getSqlDate();
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public char getVerdict() {
        return verdict;
    }

    public void setVerdict(char verdict) {
        this.verdict = verdict;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getGhostedDate() {
        return ghostedDate;
    }

    public void setGhostedDate(Long ghostedDate) {
        this.ghostedDate = ghostedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return teamScore == game.teamScore && opponentScore == game.opponentScore && verdict == game.verdict && Objects.equals(id, game.id) && Objects.equals(team, game.team) && Objects.equals(gameDate, game.gameDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, gameDate, teamScore, opponentScore, verdict);
    }
}