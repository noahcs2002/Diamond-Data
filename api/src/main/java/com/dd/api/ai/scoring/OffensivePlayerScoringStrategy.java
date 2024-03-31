package com.dd.api.ai.scoring;

import com.dd.api.restapi.models.OffensivePlayer;

public class OffensivePlayerScoringStrategy implements ScoringStrategy<OffensivePlayer> {
    @Override
    public double score(OffensivePlayer offensivePlayer) {
        return
            (double) offensivePlayer.getAtBats() * ((double) 1 /29)+
            offensivePlayer.getBattingAverage() * ((double) 1 /29)+
            (double) offensivePlayer.getCaughtStealing() * ((double) 1 /29)+
            (double) offensivePlayer.getDoubles() * ((double) 1 /29)+
            (double) offensivePlayer.getExtraBaseHits() * ((double) 1 /29)+
            (double) offensivePlayer.getGamesPlayed() * ((double) 1 /29)+
            (double) offensivePlayer.getGrandSlams() * ((double) 1 /29)+
            (double) offensivePlayer.getGroundIntoDoublePlay() * ((double) 1 /29)+
            offensivePlayer.getGroundOutAirOut() * ((double) 1 /29)+
            (double) offensivePlayer.getHitByPitch() * ((double) 1 /29)+
            (double) offensivePlayer.getHits() * ((double) 1 /29)+
            (double) offensivePlayer.getHomeRuns() * ((double) 1 /29)+
            (double) offensivePlayer.getIntentionalWalks() * ((double) 1 /29)+
            (double) offensivePlayer.getLeftOnBase() * ((double) 1 /29)+
            offensivePlayer.getOnBasePercentage() * ((double) 1 /29)+
            offensivePlayer.getOnBasePlusSlugging() * ((double) 1 /29)+
            (double) offensivePlayer.getPlateAppearances() * ((double) 1 /29)+
            (double) offensivePlayer.getReachedOnError() * ((double) 1 /29)+
            (double) offensivePlayer.getRuns() * ((double) 1 /29)+
            (double) offensivePlayer.getRunsBattedIn() * ((double) 1 /29)+
            (double) offensivePlayer.getSacrificeBunt() * ((double) 1 /29)+
            (double) offensivePlayer.getSacrificeFly() * ((double) 1 /29)+
            (double) offensivePlayer.getSingles() * ((double) 1 /29)+
            offensivePlayer.getSluggingPercentage() * ((double) 1 /29)+
            (double) offensivePlayer.getStolenBases() * ((double) 1 /29)+
            (double) offensivePlayer.getTotalBases() * ((double) 1 /29)+
            (double) offensivePlayer.getTriples() * ((double) 1 /29)+
            (double) offensivePlayer.getWalks() * ((double) 1 /29)+
            offensivePlayer.getWalkOffs();
    }
}
