package com.dd.api.restapi.calculators;

import com.dd.api.restapi.models.OffensivePlayer;

public class OffensivePlayerStatisticCalculator {

    public static OffensivePlayer updateOffensivePlayerStatistics(OffensivePlayer player) {
        player.setExtraBaseHits(player.getDoubles() + player.getTriples() + player.getHomeRuns());
        player.setTotalBases((player.getDoubles() * 2) + (player.getTriples() * 3) + (player.getHomeRuns() * 4) + (player.getSingles()));
        player.setCaughtStealingPercentage((double) (player.getCaughtStealing()) / (player.getStolenBases() + player.getCaughtStealing()));
        player.setSluggingPercentage((double) ((player.getSingles()) + (player.getDoubles() * 2) + (player.getTriples() * 3) + (player.getHomeRuns() * 4)) / player.getAtBats());
        player.setOnBasePercentage((double) (player.getHits() + player.getWalks() + player.getHitByPitch()) / (player.getAtBats() + player.getHitByPitch() + player.getSacrificeFly() + player.getWalks()));
        player.setOnBasePlusSlugging(player.getOnBasePercentage() + player.getSluggingPercentage());


        if(Double.isNaN(player.getSluggingPercentage())) {
            player.setSluggingPercentage(0);
        }

        if(Double.isNaN(player.getOnBasePercentage())) {
            player.setOnBasePercentage(0);
        }

        if(Double.isNaN(player.getCaughtStealingPercentage())) {
            player.setCaughtStealingPercentage(0);
        }

        if(Double.isNaN(player.getOnBasePlusSlugging())) {
            player.setOnBasePlusSlugging(0);
        }

        return player;
    }
}
