package com.dd.api.restapi.calculators;

import com.dd.api.restapi.models.Pitcher;

public class PitcherStatisticsCalculator {
    public static Pitcher updateStats(Pitcher pitcher) {
        pitcher.setEarnedRunAverage((9 * pitcher.getEarnedRuns()) / (pitcher.getInningsPitched()));
        pitcher.setSaveOpportunities(pitcher.getBlownSaves() + pitcher.getSaves());
        pitcher.setSavePercentage((double) (pitcher.getBlownSaves() + pitcher.getSaves()) / (pitcher.getSaveOpportunities()));
        pitcher.setWalksAndHitsPerInningPitched((pitcher.getWalks() + pitcher.getHits()) / (pitcher.getInningsPitched()));
        pitcher.setInningsPitched(updateInningsPitched(pitcher.getInningsPitched()));


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

    static double updateInningsPitched(double ip) {
        int integerComponent = (int) ip;
        double decimalComponent = ip - integerComponent;
        double compFlag = Math.round(decimalComponent * 10);

        if (compFlag == 0.0 || compFlag == 1.0 || compFlag == 2.0) {
            return ip;
        }

        if (compFlag == 3.0 || compFlag == 4.0 || compFlag == 5.0) {
            return integerComponent + 1 + ((double) (Math.round(decimalComponent * 10) % 3) / 10);
        }

        if (compFlag == 6.0 || compFlag == 7.0 || compFlag == 8.0) {
            return integerComponent + 2 + ((double) (Math.round(decimalComponent * 10) % 3) / 10);
        }

        if (compFlag == 9.0) {
            return integerComponent + 3;
        }

        return 0;
    }
}
