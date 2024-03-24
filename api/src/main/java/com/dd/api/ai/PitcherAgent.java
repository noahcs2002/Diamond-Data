package com.dd.api.ai;

import com.dd.api.restapi.models.Pitcher;
import org.hibernate.collection.spi.PersistentBag;

import java.util.List;

public class PitcherAgent {

    private final List<Pitcher> pitchers;

    public PitcherAgent(List<Pitcher> pitchers) {
        this.pitchers = pitchers;
    }

    public List<Pitcher> getSortedAndWeightedPitchers() {
        List<Pitcher> pitchers = new PersistentBag<>();
        pitchers.sort((o1, o2) -> Double.compare(computeWeightedScore(o1), computeWeightedScore(o2)));

        for (Pitcher player : this.pitchers) {
            if(pitchers.size() <= 9) {
                pitchers.add(player);
            }
            else {
                if (Double.compare(computeWeightedScore(player), computeWeightedScore(pitchers.get(pitchers.size() - 1))) > 0){
                    pitchers.remove(pitchers.get(pitchers.size() - 1));
                    pitchers.add(player);
                    pitchers.sort((o1, o2) -> Double.compare(computeWeightedScore(o1), computeWeightedScore(o2)));
                }
            }
        }
        return pitchers;
    }

    private double computeWeightedScore(Pitcher pitcher) {
        return
            (double) pitcher.getAppearances() * ((double) 1 / 30) +
            (double) pitcher.getBalks() * ((double) 1 / 30) +
            (double) pitcher.getBattersFaces() * ((double) 1 / 30) +
            (double) pitcher.getBlownSaves() * ((double) 1 / 30) +
            (double) pitcher.getCompleteGames() * ((double) 1 / 30) +
            (double) pitcher.getEarnedRuns() * ((double) 1 / 30) +
            pitcher.getEarnedRunAverage() * ((double) 1 / 30) +
            (double) pitcher.getFlyouts() * ((double) 1 / 30) +
            (double) pitcher.getGamesFinished() * ((double) 1 / 30) +
            (double) pitcher.getGamesStarted() * ((double) 1 / 30) +
            (double) pitcher.getGroundouts() * ((double) 1 / 30) +
            (double) pitcher.getHolds() * ((double) 1 / 30) +
            (double) pitcher.getInheritedRunners() * ((double) 1 / 30) +
            pitcher.getInningsPitched() * ((double) 1 / 30) +
            (double) pitcher.getLosses() * ((double) 1 / 30) +
            (double) pitcher.getNumberOfPitches() * ((double) 1 / 30) +
            (double) pitcher.getPickoffs() * ((double) 1 / 30) +
            (double) pitcher.getQualityStarts() * ((double) 1 / 30) +
            (double) pitcher.getReliefWins() * ((double) 1 / 30) +
            (double) pitcher.getSaves() * ((double) 1 / 30) +
            (double) pitcher.getSaveOpportunities() * ((double) 1 / 30) +
            pitcher.getSavePercentage() * ((double) 1 / 30) +
            (double) pitcher.getShutouts() * ((double) 1 / 30) +
            (double) pitcher.getStrikeouts() * ((double) 1 / 30) +
            (double) pitcher.getUnearnedRuns() * ((double) 1 / 30) +
            pitcher.getWalksAndHitsPerInningPitched() * ((double) 1 / 30) +
            pitcher.getWalks() * ((double) 1 / 30) +
            (double) pitcher.getWildPitches() * ((double) 1 / 30) +
            (double) pitcher.getWins() * ((double) 1 / 30) +
            pitcher.getWinningPercentage() * ((double) 1 / 30);
    }
}
