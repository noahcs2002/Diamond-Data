package com.dd.api.ai.agents;

import com.dd.api.ai.scoring.PitcherScoringStrategy;
import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.Pitcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PitcherAgentTests {

    @Test
    public void getReturnsListOfAllPlayers_WhenListIsLessThan9Elements() {

        ScoringStrategy<Pitcher> strategy = mock(ScoringStrategy.class);
        when(strategy.score(any(Pitcher.class))).thenReturn(1d);
        List<Pitcher> players = List.of(
                new Pitcher(),
                new Pitcher(),
                new Pitcher(),
                new Pitcher()
        );

        PitcherAgent sut = new PitcherAgent(new ArrayList<>(players), strategy);

        List<Pitcher> res = sut.getSortedAndWeightedPitchers();
        assertEquals(4, res.size(), 0);
    }

    @Test
    public void getReturnsSorted() {
        Pitcher one = new Pitcher();
        Pitcher two = new Pitcher();
        Pitcher three = new Pitcher();
        Pitcher four = new Pitcher();
        List<Pitcher> players = new ArrayList<>(List.of(one, two, three, four));
        Collections.shuffle(players);

        ScoringStrategy<Pitcher> strategy = mock(ScoringStrategy.class);
        when(strategy.score(one)).thenReturn(1d);
        when(strategy.score(two)).thenReturn(2d);
        when(strategy.score(three)).thenReturn(3d);
        when(strategy.score(four)).thenReturn(4d);

        List<Pitcher> expected = List.of(one, two, three, four);

        PitcherAgent sut = new PitcherAgent(new ArrayList<>(players), strategy);
        List<Pitcher> res = sut.getSortedAndWeightedPitchers();

        for(int i = 0; i < res.size(); i +=1) {
            assertEquals(expected.get(i), res.get(i));
        }

        assertEquals(4, res.size(), 0);
    }

    @Test
    public void getReturnsBestNine_WhenMoreThanNineAreMade() {
        List<Pitcher> players = new ArrayList<>();
        ScoringStrategy<Pitcher> strategy = mock(ScoringStrategy.class);

        Pitcher player1 = new Pitcher();
        player1.setId(1L);
        Pitcher player2 = new Pitcher();
        player2.setId(2L);
        Pitcher player3 = new Pitcher();
        player3.setId(3L);
        Pitcher player4 = new Pitcher();
        player4.setId(4L);
        Pitcher player5 = new Pitcher();
        player5.setId(5L);
        Pitcher player6 = new Pitcher();
        player6.setId(6L);
        Pitcher player7 = new Pitcher();
        player7.setId(7L);
        Pitcher player8 = new Pitcher();
        player8.setId(8L);
        Pitcher player9 = new Pitcher();
        player9.setId(9L);
        Pitcher player10 = new Pitcher();
        player10.setId(10L);
        Pitcher player11 = new Pitcher();
        player11.setId(11L);
        Pitcher player12 = new Pitcher();
        player12.setId(12L);

        when(strategy.score(player1)).thenReturn(1d);
        when(strategy.score(player2)).thenReturn(2d);
        when(strategy.score(player3)).thenReturn(3d);
        when(strategy.score(player4)).thenReturn(4d);
        when(strategy.score(player5)).thenReturn(5d);
        when(strategy.score(player6)).thenReturn(6d);
        when(strategy.score(player7)).thenReturn(7d);
        when(strategy.score(player8)).thenReturn(8d);
        when(strategy.score(player9)).thenReturn(9d);
        when(strategy.score(player10)).thenReturn(10d);
        when(strategy.score(player11)).thenReturn(11d);
        when(strategy.score(player12)).thenReturn(12d);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);
        players.add(player8);
        players.add(player9);
        players.add(player10);
        players.add(player11);
        players.add(player12);

        PitcherAgent sut = new PitcherAgent(new ArrayList<>(players), strategy);

        List<Pitcher> res = sut.getSortedAndWeightedPitchers();

        assertTrue(res.contains(player1));
        assertTrue(res.contains(player2));
        assertTrue(res.contains(player3));
        assertTrue(res.contains(player4));
        assertTrue(res.contains(player5));
        assertTrue(res.contains(player6));
        assertTrue(res.contains(player7));
        assertTrue(res.contains(player8));
        assertTrue(res.contains(player9));

        assertEquals(9, res.size());
    }

    @Test
    public void strangeOrderTest() {
        List<Pitcher> players = new ArrayList<>();
        ScoringStrategy<Pitcher> strategy = mock(ScoringStrategy.class);

        Pitcher player1 = new Pitcher();
        player1.setId(1L);
        Pitcher player2 = new Pitcher();
        player2.setId(2L);
        Pitcher player3 = new Pitcher();
        player3.setId(3L);
        Pitcher player4 = new Pitcher();
        player4.setId(4L);
        Pitcher player5 = new Pitcher();
        player5.setId(5L);
        Pitcher player6 = new Pitcher();
        player6.setId(6L);
        Pitcher player7 = new Pitcher();
        player7.setId(7L);
        Pitcher player8 = new Pitcher();
        player8.setId(8L);
        Pitcher player9 = new Pitcher();
        player9.setId(9L);
        Pitcher player10 = new Pitcher();
        player10.setId(10L);
        Pitcher player11 = new Pitcher();
        player11.setId(11L);
        Pitcher player12 = new Pitcher();
        player12.setId(12L);

        when(strategy.score(player1)).thenReturn(1d);
        when(strategy.score(player2)).thenReturn(2d);
        when(strategy.score(player3)).thenReturn(3d);
        when(strategy.score(player4)).thenReturn(4d);
        when(strategy.score(player5)).thenReturn(5d);
        when(strategy.score(player6)).thenReturn(6d);
        when(strategy.score(player7)).thenReturn(7d);
        when(strategy.score(player8)).thenReturn(8d);
        when(strategy.score(player9)).thenReturn(9d);
        when(strategy.score(player10)).thenReturn(10d);
        when(strategy.score(player11)).thenReturn(11d);
        when(strategy.score(player12)).thenReturn(12d);

        players.add(player1);
        players.add(player4);
        players.add(player2);
        players.add(player6);
        players.add(player7);
        players.add(player3);
        players.add(player8);
        players.add(player5);
        players.add(player10);
        players.add(player12);
        players.add(player9);

        PitcherAgent sut = new PitcherAgent(new ArrayList<>(players), strategy);

        List<Pitcher> res = sut.getSortedAndWeightedPitchers();

        assertTrue(res.contains(player1));
        assertTrue(res.contains(player2));
        assertTrue(res.contains(player3));
        assertTrue(res.contains(player4));
        assertTrue(res.contains(player5));
        assertTrue(res.contains(player6));
        assertTrue(res.contains(player7));
        assertTrue(res.contains(player8));
        assertTrue(res.contains(player9));

        assertEquals(9, res.size());
    }

    @Test
    public void agentComputesScoreCorrectlyWhenUsingDefaultBehaviour() {
        double scale = 1/30.0;

        Pitcher pitcher = new Pitcher(
                "John",                  // firstName
                "Doe",                   // lastName
                "R",                     // preference (assumed to be 'R' for right-handed)
                null,                    // Team object
                30,                      // appearances
                2,                       // balks
                500,                     // battersFaces
                3,                       // blownSaves
                2,                       // completeGames
                50,                      // earnedRuns
                3.75,                    // earnedRunAverage
                40,                      // flyouts
                20,                      // gamesFinished
                10,                      // gamesStarted
                60,                      // groundouts
                5,                       // holds
                120,                     // hits
                10,                      // inheritedRunners
                200.0,                   // inningsPitched
                8,                       // losses
                3000,                    // numberOfPitches
                3,                       // pickoffs
                5,                       // qualityStarts
                4,                       // reliefWins
                15,                      // saves
                20,                      // saveOpportunities
                0.75,                    // savePercentage
                2,                       // shutouts
                180,                     // strikeouts
                15,                      // unearnedRuns
                40,                      // walks
                1.25,                    // walksAndHitsPerInningPitched
                5,                       // wildPitches
                10,                      // wins
                0.667,                    // winningPercentage
                ""
        );

        double expected = (30 + 2 + 500 + 3 + 2 + 50 + 3.75 + 40 + 20 + 10 + 60 + 5 + 120 + 10 + 200.0 + 8 + 3000 + 3 + 5 + 4 + 15 + 20 + 0.75 + 2 + 180 + 15 + 40 + 1.25 + 5 + 10 + 0.667) * scale;

        PitcherAgent agent = new PitcherAgent(List.of(pitcher));
        double score = agent.computeWeightedScore(pitcher);

        assertEquals(expected, score, 5);
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullPitchers() {
        new PitcherAgent(null, new PitcherScoringStrategy());
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullStrategy() {
        new PitcherAgent(new ArrayList<>(List.of()), null);
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullPlayers_DefaultStrategyConstructor() {
        new PitcherAgent(null);
    }
}