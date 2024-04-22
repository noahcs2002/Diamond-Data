package com.dd.api.restapi.calculators;

import com.dd.api.restapi.models.DefensivePlayer;

public class DefensivePlayerStatisticsCalculator {

    public static DefensivePlayer updateStatistics(DefensivePlayer player) {
        player.setCaughtStealingPercent((player.getStolenBaseAttempts() + player.getStolenBasesAllowed()) / player.getStolenBasesAllowed());
        player.setTotalChances(player.getAssists() + player.getErrors() + player.getPutouts());
        player.setFieldingPercentage((double) (player.getPutouts() + player.getAssists()) / (player.getAssists() + player.getPutouts() + player.getErrors()));

        if(Double.isNaN(player.getCaughtStealingPercent())) {
            player.setCaughtStealingPercent(0);
        }

        if(Double.isNaN(player.getFieldingPercentage())) {
            player.setFieldingPercentage(0);
        }

        return player;
    }
}
