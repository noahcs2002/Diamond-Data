package com.dd.api.ai.scoring;

import com.dd.api.restapi.models.OffensivePlayer;

public class OffensivePlayerScoringStrategy implements ScoringStrategy<OffensivePlayer> {
    @Override
    public double score(OffensivePlayer offensivePlayer) {
        double factor = 1/29.0;
        return
            (double) offensivePlayer.getAtBats() * ((double) factor)+
            offensivePlayer.getBattingAverage() * ((double) factor)+
            (double) offensivePlayer.getCaughtStealing() * ((double) factor)+
            offensivePlayer.getCaughtStealingPercentage() * factor +
            (double) offensivePlayer.getDoubles() * ((double) factor)+
            (double) offensivePlayer.getExtraBaseHits() * ((double) factor)+
            (double) offensivePlayer.getGamesPlayed() * ((double) factor)+
            (double) offensivePlayer.getGrandSlams() * ((double) factor)+
            (double) offensivePlayer.getGroundIntoDoublePlay() * ((double) factor)+
            (double) offensivePlayer.getHitByPitch() * ((double) factor)+
            (double) offensivePlayer.getHits() * ((double) factor)+
            (double) offensivePlayer.getHomeRuns() * ((double) factor)+
            (double) offensivePlayer.getIntentionalWalks() * ((double) factor)+
            (double) offensivePlayer.getLeftOnBase() * ((double) factor)+
            offensivePlayer.getOnBasePercentage() * ((double) factor)+
            offensivePlayer.getOnBasePlusSlugging() * ((double) factor)+
            (double) offensivePlayer.getPlateAppearances() * ((double) factor)+
            (double) offensivePlayer.getReachedOnError() * ((double) factor)+
            (double) offensivePlayer.getRuns() * ((double) factor)+
            (double) offensivePlayer.getRunsBattedIn() * ((double) factor)+
            (double) offensivePlayer.getSacrificeBunt() * ((double) factor)+
            (double) offensivePlayer.getSacrificeFly() * ((double) factor)+
            (double) offensivePlayer.getSingles() * ((double) factor)+
            offensivePlayer.getSluggingPercentage() * ((double) factor)+
            (double) offensivePlayer.getStolenBases() * ((double) factor)+
            (double) offensivePlayer.getTotalBases() * ((double) factor)+
            (double) offensivePlayer.getTriples() * ((double) factor)+
            (double) offensivePlayer.getWalks() * ((double) factor)+
            offensivePlayer.getWalkOffs();
    }
}
