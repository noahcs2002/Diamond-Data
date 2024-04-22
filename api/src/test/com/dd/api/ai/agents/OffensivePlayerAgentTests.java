package com.dd.api.ai.agents;

import com.dd.api.ai.scoring.OffensivePlayerScoringStrategy;
import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.OffensivePlayer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OffensivePlayerAgentTests {

    @Test
    public void getReturnsListOfAllPlayers_WhenListIsLessThan9Elements() {

        ScoringStrategy<OffensivePlayer> strategy = mock(ScoringStrategy.class);
        when(strategy.score(any(OffensivePlayer.class))).thenReturn(1d);
        List<OffensivePlayer> players = List.of(
                new OffensivePlayer(),
                new OffensivePlayer(),
                new OffensivePlayer(),
                new OffensivePlayer()
        );

        OffensivePlayerAgent sut = new OffensivePlayerAgent(new ArrayList<>(players), strategy);

        List<OffensivePlayer> res = sut.getSortedAndWeightedOffensivePlayers();
        assertEquals(4, res.size(), 0);
    }

    @Test
    public void getReturnsSorted() {
        OffensivePlayer one = new OffensivePlayer();
        OffensivePlayer two = new OffensivePlayer();
        OffensivePlayer three = new OffensivePlayer();
        OffensivePlayer four = new OffensivePlayer();
        List<OffensivePlayer> players = new ArrayList<>(List.of(one, two, three, four));
        Collections.shuffle(players);

        ScoringStrategy<OffensivePlayer> strategy = mock(ScoringStrategy.class);
        when(strategy.score(one)).thenReturn(1d);
        when(strategy.score(two)).thenReturn(2d);
        when(strategy.score(three)).thenReturn(3d);
        when(strategy.score(four)).thenReturn(4d);

        List<OffensivePlayer> expected = List.of(one, two, three, four);

        OffensivePlayerAgent sut = new OffensivePlayerAgent(new ArrayList<>(players), strategy);
        List<OffensivePlayer> res = sut.getSortedAndWeightedOffensivePlayers();

        for(int i = 0; i < res.size(); i +=1) {
            assertEquals(expected.get(i), res.get(i));
        }

        assertEquals(4, res.size(), 0);
    }

    @Test
    public void getReturnsBestNine_WhenMoreThanNineAreMade() {
        List<OffensivePlayer> players = new ArrayList<>();
        ScoringStrategy<OffensivePlayer> strategy = mock(ScoringStrategy.class);

        OffensivePlayer player1 = new OffensivePlayer();
        player1.setId(1L);
        OffensivePlayer player2 = new OffensivePlayer();
        player2.setId(2L);
        OffensivePlayer player3 = new OffensivePlayer();
        player3.setId(3L);
        OffensivePlayer player4 = new OffensivePlayer();
        player4.setId(4L);
        OffensivePlayer player5 = new OffensivePlayer();
        player5.setId(5L);
        OffensivePlayer player6 = new OffensivePlayer();
        player6.setId(6L);
        OffensivePlayer player7 = new OffensivePlayer();
        player7.setId(7L);
        OffensivePlayer player8 = new OffensivePlayer();
        player8.setId(8L);
        OffensivePlayer player9 = new OffensivePlayer();
        player9.setId(9L);
        OffensivePlayer player10 = new OffensivePlayer();
        player10.setId(10L);
        OffensivePlayer player11 = new OffensivePlayer();
        player11.setId(11L);
        OffensivePlayer player12 = new OffensivePlayer();
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

        OffensivePlayerAgent sut = new OffensivePlayerAgent(new ArrayList<>(players), strategy);

        List<OffensivePlayer> res = sut.getSortedAndWeightedOffensivePlayers();

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
        List<OffensivePlayer> players = new ArrayList<>();
        ScoringStrategy<OffensivePlayer> strategy = mock(ScoringStrategy.class);

        OffensivePlayer player1 = new OffensivePlayer();
        player1.setId(1L);
        OffensivePlayer player2 = new OffensivePlayer();
        player2.setId(2L);
        OffensivePlayer player3 = new OffensivePlayer();
        player3.setId(3L);
        OffensivePlayer player4 = new OffensivePlayer();
        player4.setId(4L);
        OffensivePlayer player5 = new OffensivePlayer();
        player5.setId(5L);
        OffensivePlayer player6 = new OffensivePlayer();
        player6.setId(6L);
        OffensivePlayer player7 = new OffensivePlayer();
        player7.setId(7L);
        OffensivePlayer player8 = new OffensivePlayer();
        player8.setId(8L);
        OffensivePlayer player9 = new OffensivePlayer();
        player9.setId(9L);
        OffensivePlayer player10 = new OffensivePlayer();
        player10.setId(10L);
        OffensivePlayer player11 = new OffensivePlayer();
        player11.setId(11L);
        OffensivePlayer player12 = new OffensivePlayer();
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

        OffensivePlayerAgent sut = new OffensivePlayerAgent(new ArrayList<>(players), strategy);

        List<OffensivePlayer> res = sut.getSortedAndWeightedOffensivePlayers();

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
        double scale = 1/29.0;

        OffensivePlayer player = new OffensivePlayer(
                null,
                500,                   // atBats
                0.320,                 // battingAverage
                5,                     // caughtStealing
                0.750,                 // caughtStealingPercentage
                30,                    // doubles
                50,                    // extraBaseHits
                150,                   // gamesPlayed
                2,                     // grandSlams
                10,                    // groundIntoDoublePlay
                8,                     // hitByPitch
                160,                   // hits
                25,                    // homeRuns
                20,                    // intentionalWalks
                40,                    // leftOnBase
                0.400,                 // onBasePercentage
                0.850,                 // onBasePlusSlugging
                550,                   // plateAppearances
                6,                     // reachedOnError
                80,                    // runs
                70,                    // runsBattedIn
                5,                     // sacrificeBunt
                7,                     // sacrificeFly
                0,                     // strikeouts
                120,                   // singles
                0.550,                 // sluggingPercentage
                15,                    // stolenBases
                280,                   // totalBases
                5,                     // triples
                60,                    // walks
                2                      // walkOffs
        );


        double expectedScore = (500+.32+5+.75+30+50+150+2+10+8+160+25+20+40+.4
                +.85+550+6+80+70+5+7+120+.55+15+280+5+60+2) * scale;

        OffensivePlayerAgent sut = new OffensivePlayerAgent(new ArrayList<>(List.of(player)));
        double score = sut.computeWeightedScore(player);

        assertEquals(expectedScore, score, 2);
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullOffensivePlayers() {
        new OffensivePlayerAgent(null, new OffensivePlayerScoringStrategy());
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullStrategy() {
        new OffensivePlayerAgent(new ArrayList<>(List.of()), null);
    }

    @Test(expected = NullPointerException.class)
    public void agentFailsOnNullPlayers_DefaultStrategyConstructor() {
        new OffensivePlayerAgent(null);
    }
}