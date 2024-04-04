package com.dd.api.ai.agents;

import com.dd.api.ai.scoring.PitcherScoringStrategy;
import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.Pitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PitcherAgent {

    private final List<Pitcher> pitchers;
    private final ScoringStrategy<Pitcher> scoringStrategy;

    public PitcherAgent(List<Pitcher> pitchers, ScoringStrategy<Pitcher> scoringStrategy) {
        Objects.requireNonNull(pitchers);
        Objects.requireNonNull(scoringStrategy);
        this.pitchers = pitchers;
        this.scoringStrategy = scoringStrategy;
    }

    public PitcherAgent(List<Pitcher> pitchers) {
        Objects.requireNonNull(pitchers);
        this.pitchers = pitchers;
        this.scoringStrategy = new PitcherScoringStrategy();
    }

    public List<Pitcher> getSortedAndWeightedPitchers() {
        pitchers.sort((p1, p2) -> {
            double score1 = computeWeightedScore(p1);
            double score2 = computeWeightedScore(p2);
            return Double.compare(score1, score2);
        });

        return new ArrayList<>(pitchers.subList(0, Math.min(9, pitchers.size())));
    }

    double computeWeightedScore(Pitcher pitcher) {
        return this.scoringStrategy.score(pitcher);
    }
}