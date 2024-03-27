package com.dd.api.ai.scoring;

import com.dd.api.restapi.models.DefensivePlayer;

public class DefensivePlayerScoringStrategy implements ScoringStrategy<DefensivePlayer> {
    @Override
    public double score(DefensivePlayer defensivePlayer) {
        return
            (double) defensivePlayer.getAssists() * ((double) 1 /12)+
            defensivePlayer.getCaughtStealingPercent() * ((double) 1 /12)+
            (double) defensivePlayer.getDoublePlay() * ((double) 1 /12)+
            (double) defensivePlayer.getErrors() * ((double) 1 /12) +
            defensivePlayer.getFieldingPercentage() * ((double) 1 /12) +
            (double) defensivePlayer.getInningsPlayed() * ((double) 1 /12) +
            (double) defensivePlayer.getOuts() * ((double) 1 /12) +
            (double) defensivePlayer.getOutfieldAssists() * ((double) 1 /12) +
            (double) defensivePlayer.getPassedBalls() * ((double) 1 /12) +
            (double) defensivePlayer.getPutouts() * ((double) 1 /12) +
            (double) defensivePlayer.getTotalChances() * ((double) 1 /12) +
            (double) defensivePlayer.getTriplePlays() * ((double) 1 /12);
    }
}
