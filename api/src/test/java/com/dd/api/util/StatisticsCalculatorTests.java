package com.dd.api.util;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Pitcher;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsCalculatorTests {
    
    @Test
    public void simpleUpdateOnBasePercentageTest() {
	OffensivePlayer player = new OffensivePlayer();
	player.setHits(100);
	player.setWalks(50);
	player.setHitByPitch(10);
	player.setAtBats(200);
	player.setSacrificeFlies(25);
	
	double e = (double) (100 + 50 + 10) / (200 + 50 + 10 + 25);
	StatisticsCalculator.updateOnBasePercentage(player);
	assertEquals(e, player.getOnBasePercentage(), 0.1);
    }
    
    @Test
    public void simpleUpdateSluggingTest() {
	    OffensivePlayer player = new OffensivePlayer();
	    player.setTotalBases(50);
	    player.setAtBats(100);
	    
	    double exp = (double) 50 / 100;
	    
	    StatisticsCalculator.updateSlugging(player);
	    assertEquals(exp, player.getSlugging(), 0.1);
    }
    
    @Test
    public void simpleUpdateOnBasePlusSluggingTest() {
	OffensivePlayer player = new OffensivePlayer();
	player.setSlugging(1.5);
	player.setOnBasePercentage(43.5);
	double exp = 45;
	
	StatisticsCalculator.updateOnBasePlusSlugging(player);
	
	assertEquals(exp, player.getOnBasePlusSlugging(), 0.1);
	
    }
    
    @Test
    public void simpleUpdateAverageTest() {
	OffensivePlayer player = new OffensivePlayer();
	player.setHits(100);
	player.setAtBats(200);
	double exp = 100.0/200.0;
	
	StatisticsCalculator.updateBattingAverage(player);
	assertEquals(exp, player.getAverage(), 0.1);
    }
    
    @Test
    public void simpleUpdateExtraBaseHitsTest() {
	OffensivePlayer player = new OffensivePlayer();
	player.setDoubles(100);
	player.setTriples(30);
	player.setHomeRuns(12);
	int exp = 142;
	
	StatisticsCalculator.updateExtraBaseHits(player);
	
	assertEquals(exp, player.getExtraBaseHits());
    }
    
    @Test
    public void simpleUpdateTotalBasesTest() {
	OffensivePlayer player = new OffensivePlayer();
	player.setSingles(10);
	player.setDoubles(20);
	player.setTriples(30);
	player.setHomeRuns(40);
	double exp = 10 + 2 * 20 + 3 * 30 + 4 * 40;
	
	StatisticsCalculator.updateTotalBases(player);
	
	assertEquals(exp, player.getTotalBases(), 0);
    }
    
    @Test
    public void simpleUpdateFieldingPercentageTest() {
	DefensivePlayer player = new DefensivePlayer();
	player.setAssists(10);
	player.setPutouts(50);
	player.setErrors(10);
	double expected = (double) (10 + 50) / (10 + 50 + 10);
	
	StatisticsCalculator.updateFieldingPercentage(player);
	assertEquals(expected, player.getFieldingPercentage(), .1);
    }
    
    @Test
    public void simpleUpdateERATest() {
	Pitcher pitcher = new Pitcher();
	pitcher.setEarnedRunsAllowed(100);
	pitcher.setInningsPitched(2000);
	double exp = (double) 9 * 100 / 2000;
	
	StatisticsCalculator.updateERA(pitcher);
	
	assertEquals(exp, pitcher.getEarnedRunAverage(), 0.1);
    }
    
    @Test
    public void simpleUpdateSavePercentageTest() {
	Pitcher pitcher = new Pitcher();
	pitcher.setSaves(25);
	pitcher.setSaveOpportunities(30);
	
	double exp = (double) 25 / 30;
	
	StatisticsCalculator.updateSavePercentage(pitcher);
	
	assertEquals(exp, pitcher.getSavePercentage(), 0.1);
    }
    
    @Test
    public void simpleUpdateWinningPercentageTest() {
	Pitcher pitcher = new Pitcher();
	pitcher.setWins(100);
	pitcher.setLosses(200);
	double exp = (double) 100 / (100 + 200);
	
	StatisticsCalculator.updateWinningPercentage(pitcher);
	
	assertEquals(exp, pitcher.getWinningPercentage(), 0.1);
    }
}