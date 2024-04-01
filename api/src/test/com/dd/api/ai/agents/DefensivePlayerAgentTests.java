package com.dd.api.ai.agents;

import com.dd.api.ai.scoring.DefensivePlayerScoringStrategy;
import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.DefensivePlayer;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefensivePlayerAgentTests {

    @Test
    public void getReturnsListOfAllPlayers_WhenListIsLessThan9Elements() {

        ScoringStrategy<DefensivePlayer> strategy = mock(ScoringStrategy.class);
        when(strategy.score(any(DefensivePlayer.class))).thenReturn(1d);
        List<DefensivePlayer> players = List.of(
                new DefensivePlayer(),
                new DefensivePlayer(),
                new DefensivePlayer(),
                new DefensivePlayer()
        );

        DefensivePlayerAgent sut = new DefensivePlayerAgent(new ArrayList<>(players), strategy);

        List<DefensivePlayer> res = sut.getSortedAndWeightedDefensivePlayers();
        assertEquals(4, res.size(), 0);
    }

    @Test
    public void getReturnsSorted() {
        DefensivePlayer one = new DefensivePlayer();
        DefensivePlayer two = new DefensivePlayer();
        DefensivePlayer three = new DefensivePlayer();
        DefensivePlayer four = new DefensivePlayer();
        List<DefensivePlayer> players = new ArrayList<>(List.of(one, two, three, four));
        Collections.shuffle(players);

        ScoringStrategy<DefensivePlayer> strategy = mock(ScoringStrategy.class);
        when(strategy.score(one)).thenReturn(1d);
        when(strategy.score(two)).thenReturn(2d);
        when(strategy.score(three)).thenReturn(3d);
        when(strategy.score(four)).thenReturn(4d);

        List<DefensivePlayer> expected = List.of(one, two, three, four);

        DefensivePlayerAgent sut = new DefensivePlayerAgent(new ArrayList<>(players), strategy);
        List<DefensivePlayer> res = sut.getSortedAndWeightedDefensivePlayers();

        for(int i = 0; i < res.size(); i +=1) {
            assertEquals(expected.get(i), res.get(i));
        }

        assertEquals(4, res.size(), 0);
    }

    @Test
    public void getReturnsBestNine_WhenMoreThanNineAreMade() {
        List<DefensivePlayer> players = new ArrayList<>();
        ScoringStrategy<DefensivePlayer> strategy = mock(ScoringStrategy.class);

        DefensivePlayer defensivePlayer1 = new DefensivePlayer();
        defensivePlayer1.setId(1L);
        DefensivePlayer defensivePlayer2 = new DefensivePlayer();
        defensivePlayer2.setId(2L);
        DefensivePlayer defensivePlayer3 = new DefensivePlayer();
        defensivePlayer3.setId(3L);
        DefensivePlayer defensivePlayer4 = new DefensivePlayer();
        defensivePlayer4.setId(4L);
        DefensivePlayer defensivePlayer5 = new DefensivePlayer();
        defensivePlayer5.setId(5L);
        DefensivePlayer defensivePlayer6 = new DefensivePlayer();
        defensivePlayer6.setId(6L);
        DefensivePlayer defensivePlayer7 = new DefensivePlayer();
        defensivePlayer7.setId(7L);
        DefensivePlayer defensivePlayer8 = new DefensivePlayer();
        defensivePlayer8.setId(8L);
        DefensivePlayer defensivePlayer9 = new DefensivePlayer();
        defensivePlayer9.setId(9L);
        DefensivePlayer defensivePlayer10 = new DefensivePlayer();
        defensivePlayer10.setId(10L);
        DefensivePlayer defensivePlayer11 = new DefensivePlayer();
        defensivePlayer11.setId(11L);
        DefensivePlayer defensivePlayer12 = new DefensivePlayer();
        defensivePlayer12.setId(12L);

        when(strategy.score(defensivePlayer1)).thenReturn(1d);
        when(strategy.score(defensivePlayer2)).thenReturn(2d);
        when(strategy.score(defensivePlayer3)).thenReturn(3d);
        when(strategy.score(defensivePlayer4)).thenReturn(4d);
        when(strategy.score(defensivePlayer5)).thenReturn(5d);
        when(strategy.score(defensivePlayer6)).thenReturn(6d);
        when(strategy.score(defensivePlayer7)).thenReturn(7d);
        when(strategy.score(defensivePlayer8)).thenReturn(8d);
        when(strategy.score(defensivePlayer9)).thenReturn(9d);
        when(strategy.score(defensivePlayer10)).thenReturn(10d);
        when(strategy.score(defensivePlayer11)).thenReturn(11d);
        when(strategy.score(defensivePlayer12)).thenReturn(12d);

        players.add(defensivePlayer1);
        players.add(defensivePlayer2);
        players.add(defensivePlayer3);
        players.add(defensivePlayer4);
        players.add(defensivePlayer5);
        players.add(defensivePlayer6);
        players.add(defensivePlayer7);
        players.add(defensivePlayer8);
        players.add(defensivePlayer9);
        players.add(defensivePlayer10);
        players.add(defensivePlayer11);
        players.add(defensivePlayer12);

        DefensivePlayerAgent sut = new DefensivePlayerAgent(new ArrayList<>(players), strategy);

        List<DefensivePlayer> res = sut.getSortedAndWeightedDefensivePlayers();

        assertTrue(res.contains(defensivePlayer1));
        assertTrue(res.contains(defensivePlayer2));
        assertTrue(res.contains(defensivePlayer3));
        assertTrue(res.contains(defensivePlayer4));
        assertTrue(res.contains(defensivePlayer5));
        assertTrue(res.contains(defensivePlayer6));
        assertTrue(res.contains(defensivePlayer7));
        assertTrue(res.contains(defensivePlayer8));
        assertTrue(res.contains(defensivePlayer9));

        assertEquals(9, res.size());
    }

    @Test
    public void strangeOrderTest() {
        List<DefensivePlayer> players = new ArrayList<>();
        ScoringStrategy<DefensivePlayer> strategy = mock(ScoringStrategy.class);

        DefensivePlayer defensivePlayer1 = new DefensivePlayer();
        defensivePlayer1.setId(1L);
        DefensivePlayer defensivePlayer2 = new DefensivePlayer();
        defensivePlayer2.setId(2L);
        DefensivePlayer defensivePlayer3 = new DefensivePlayer();
        defensivePlayer3.setId(3L);
        DefensivePlayer defensivePlayer4 = new DefensivePlayer();
        defensivePlayer4.setId(4L);
        DefensivePlayer defensivePlayer5 = new DefensivePlayer();
        defensivePlayer5.setId(5L);
        DefensivePlayer defensivePlayer6 = new DefensivePlayer();
        defensivePlayer6.setId(6L);
        DefensivePlayer defensivePlayer7 = new DefensivePlayer();
        defensivePlayer7.setId(7L);
        DefensivePlayer defensivePlayer8 = new DefensivePlayer();
        defensivePlayer8.setId(8L);
        DefensivePlayer defensivePlayer9 = new DefensivePlayer();
        defensivePlayer9.setId(9L);
        DefensivePlayer defensivePlayer10 = new DefensivePlayer();
        defensivePlayer10.setId(10L);
        DefensivePlayer defensivePlayer11 = new DefensivePlayer();
        defensivePlayer11.setId(11L);
        DefensivePlayer defensivePlayer12 = new DefensivePlayer();
        defensivePlayer12.setId(12L);

        when(strategy.score(defensivePlayer1)).thenReturn(1d);
        when(strategy.score(defensivePlayer2)).thenReturn(2d);
        when(strategy.score(defensivePlayer3)).thenReturn(3d);
        when(strategy.score(defensivePlayer4)).thenReturn(4d);
        when(strategy.score(defensivePlayer5)).thenReturn(5d);
        when(strategy.score(defensivePlayer6)).thenReturn(6d);
        when(strategy.score(defensivePlayer7)).thenReturn(7d);
        when(strategy.score(defensivePlayer8)).thenReturn(8d);
        when(strategy.score(defensivePlayer9)).thenReturn(9d);
        when(strategy.score(defensivePlayer10)).thenReturn(10d);
        when(strategy.score(defensivePlayer11)).thenReturn(11d);
        when(strategy.score(defensivePlayer12)).thenReturn(12d);

        players.add(defensivePlayer1);
        players.add(defensivePlayer4);
        players.add(defensivePlayer2);
        players.add(defensivePlayer6);
        players.add(defensivePlayer7);
        players.add(defensivePlayer3);
        players.add(defensivePlayer8);
        players.add(defensivePlayer5);
        players.add(defensivePlayer10);
        players.add(defensivePlayer12);
        players.add(defensivePlayer9);

        DefensivePlayerAgent sut = new DefensivePlayerAgent(new ArrayList<>(players), strategy);

        List<DefensivePlayer> res = sut.getSortedAndWeightedDefensivePlayers();

        assertTrue(res.contains(defensivePlayer1));
        assertTrue(res.contains(defensivePlayer2));
        assertTrue(res.contains(defensivePlayer3));
        assertTrue(res.contains(defensivePlayer4));
        assertTrue(res.contains(defensivePlayer5));
        assertTrue(res.contains(defensivePlayer6));
        assertTrue(res.contains(defensivePlayer7));
        assertTrue(res.contains(defensivePlayer8));
        assertTrue(res.contains(defensivePlayer9));

        assertEquals(9, res.size());
    }

    @Test
    public void agentComputesScoreCorrectlyWhenUsingDefaultBehaviour() {
        DefensivePlayer player = new DefensivePlayer();
        player.setAssists(12);
        player.setCaughtStealingPercent(12.3);
        player.setDoublePlay(42);
        player.setErrors(1);
        player.setFieldingPercentage(59);
        player.setInningsPlayed(432);
        player.setOuts(368);
        player.setOutfieldAssists(51);
        player.setPassedBalls(6);
        player.setPutouts(49);
        player.setTotalChances(492);
        player.setTriplePlays(4);

        double expectedScore = (
            (12d * (1d / 12d)) +
            (12.3 * (1d/ 12d)) +
            (42 * (1d/12d)) +
            (1 * (1d/12d)) +
            (59 * (1d/12d)) +
            (432 * (1d/12d)) +
            (368 * (1d/12d)) +
            (51 * (1d/12d)) +
            (6 * (1d/12d)) +
            (49 * (1d/12d)) +
            (492 * (1d/12d)) +
            (4 * (1d/12d))
        );

        DefensivePlayerAgent sut = new DefensivePlayerAgent(new ArrayList<>(List.of(player)));
        double score = sut.computeWeightedScore(player);

        assertEquals(expectedScore, score, 0.1);
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullDefensivePlayers() {
        new DefensivePlayerAgent(null, new DefensivePlayerScoringStrategy());
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullStrategy() {
        new DefensivePlayerAgent(new ArrayList<>(List.of()), null);
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullPlayers_DefaultStrategyConstructor() {
        new DefensivePlayerAgent(null);
    }
}