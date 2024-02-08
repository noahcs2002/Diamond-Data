package com.dd.api.util;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Pitcher;

public class StatisticsCalculator {
    
    public static OffensivePlayer updateOnBasePercentage(OffensivePlayer player) {
        // OBP = (Hits + walks + HBP) / (AB + BB + HBP + SF)
        double obp = (double) (player.getHits() + player.getWalks() + player.getHitByPitch()) / (player.getAtBats() + player.getWalks() + player.getHitByPitch() + player.getSacrificeFlies());
        player.setOnBasePercentage(obp);
        return player;
    }
    
    public static OffensivePlayer updateOnBasePlusSlugging(OffensivePlayer player) {
        // OPS = OBP + Slug
        player.setOnBasePlusSlugging(player.getSlugging() + player.getOnBasePercentage());
        return player;
    }
    
    public static OffensivePlayer updateSlugging(OffensivePlayer player) {
        // slug = totalBases / at-bats
        
        player.setSlugging((double) player.getTotalBases() / player.getAtBats());
        return player;
    }
    
    public static OffensivePlayer updateCaughtStealingPercentage(OffensivePlayer player) {
        // CS% = 1 - (SB / SBA) using inverse statistics law
        
        player.setCaughtStealingPercentage((1.0d - ((double) player.getStolenBases() / player.getStolenBaseAttempts())));
        return player;
    }
    
    public static OffensivePlayer updateBattingAverage(OffensivePlayer player) {
        player.setAverage( (double) player.getHits() / player.getAtBats());
        return player;
    }
    
    public static OffensivePlayer updateExtraBaseHits(OffensivePlayer player) {
        player.setExtraBaseHits(player.getDoubles() + player.getTriples() + player.getHomeRuns());
        return player;
    }
    
    public static OffensivePlayer updateTotalBases(OffensivePlayer player) {
        // total bases = singles + 2 * doubles + 3 * triples + 4 * homeruns
        player.setTotalBases(player.getSingles() + 2 * player.getDoubles() + 3 * player.getTriples() + 4 * player.getHomeRuns());
        return player;
    }
    
    public static DefensivePlayer updateFieldingPercentage(DefensivePlayer player) {
        // FP = A + PO / A + PO + E
        double fieldingPercentage = (double)
            (player.getAssists() + player.getPutouts()) / (player.getAssists() + player.getPutouts() + player.getErrors());
        player.setFieldingPercentage(fieldingPercentage);
        return player;
    }
    
    public static Pitcher updateERA(Pitcher pitcher) {
        // ERA = 9 * ER / IP
        double era = (double)
            (9 * pitcher.getEarnedRunsAllowed()) / (pitcher.getInningsPitched());
        pitcher.setEarnedRunAverage(era);
        return pitcher;
    }
    
    public static Pitcher updateSavePercentage(Pitcher pitcher) {
        // SV% = SV / SVO
        double savePercent = (double) pitcher.getSaves() / pitcher.getSaveOpportunities();
        pitcher.setSavePercentage(savePercent);
        return pitcher;
    }
    
    public static Pitcher updateWHIP(Pitcher pitcher) {
        // TODO: Implement walks allowed by pitchers because somehow I forgot this.
        return pitcher;
    }
    
    public static Pitcher updateWinningPercentage(Pitcher pitcher) {
        pitcher.setWinningPercentage((double) pitcher.getWins() / (pitcher.getWins() + pitcher.getLosses()));
        return pitcher;
    }
    
    public static Pitcher updateAll(Pitcher pitcher) {
        updateWinningPercentage(pitcher);
        updateERA(pitcher);
        updateSavePercentage(pitcher);
        return pitcher;
    }
    
    public static OffensivePlayer updateAll(OffensivePlayer player) {
        updateBattingAverage(player);
        updateTotalBases(player);
        updateExtraBaseHits(player);
        updateOnBasePlusSlugging(player);
        updateSlugging(player);
        updateOnBasePercentage(player);
        updateCaughtStealingPercentage(player);
        return player;
    }
    
    public static DefensivePlayer updateAll(DefensivePlayer player) {
        updateFieldingPercentage(player);
        return player;
    }
}