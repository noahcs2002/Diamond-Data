package com.dd.api.restapi.calculators;

import com.dd.api.restapi.models.Pitcher;

public class PitcherStatisticsCalculator {
    public static Pitcher updateStats(Pitcher pitcher) {
        pitcher.setEarnedRunAverage((9 * pitcher.getEarnedRuns()) / (pitcher.getInningsPitched()));
        pitcher.setSaveOpportunities(pitcher.getBlownSaves() + pitcher.getSaves());
        pitcher.setSavePercentage((double) (pitcher.getBlownSaves() + pitcher.getSaves()) / (pitcher.getSaveOpportunities()));
        pitcher.setWalksAndHitsPerInningPitched((pitcher.getWalks() + pitcher.getHits()) / (pitcher.getInningsPitched()));

        if(Double.isNaN(pitcher.getEarnedRunAverage())) {
            pitcher.setEarnedRunAverage(0);
        }

        if(Double.isNaN(pitcher.getWalksAndHitsPerInningPitched())) {
            pitcher.setWalksAndHitsPerInningPitched(0);
        }

        if(Double.isNaN(pitcher.getSavePercentage())) {
            pitcher.setSavePercentage(0);
        }

        return pitcher;
    }
}
