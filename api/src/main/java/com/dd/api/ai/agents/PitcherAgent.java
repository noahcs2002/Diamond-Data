package com.dd.api.ai.agents;

import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.Pitcher;
import org.hibernate.collection.spi.PersistentBag;

import java.util.ArrayList;
import java.util.List;

public class PitcherAgent {

    private final List<Pitcher> pitchers;
    private final ScoringStrategy<Pitcher> scoringStrategy;

    public PitcherAgent(List<Pitcher> pitchers, ScoringStrategy<?> scoringStrategy) {
        this.pitchers = pitchers;
        this.scoringStrategy = (ScoringStrategy<Pitcher>) scoringStrategy;
    }

    public List<Pitcher> getSortedAndWeightedPitchers() {
        List<Pitcher> pitchers = new ArrayList<>();

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
        return this.scoringStrategy.score(pitcher);
    }
}