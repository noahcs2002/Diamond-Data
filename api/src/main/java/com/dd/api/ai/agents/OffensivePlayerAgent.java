package com.dd.api.ai.agents;

import com.dd.api.ai.scoring.OffensivePlayerScoringStrategy;
import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.OffensivePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OffensivePlayerAgent {

    private final List<OffensivePlayer> offensivePlayers;
    private final ScoringStrategy<OffensivePlayer> scoringStrategy;

    public OffensivePlayerAgent(List<OffensivePlayer> offensivePlayers, ScoringStrategy<OffensivePlayer> scoringStrategy) {
        Objects.requireNonNull(offensivePlayers);
        Objects.requireNonNull(scoringStrategy);
        this.offensivePlayers = offensivePlayers;
        this.scoringStrategy = scoringStrategy;
    }

    public OffensivePlayerAgent(List<OffensivePlayer> offensivePlayers) {
        Objects.requireNonNull(offensivePlayers);
        this.offensivePlayers = offensivePlayers;
        this.scoringStrategy = new OffensivePlayerScoringStrategy();
    }

    public List<OffensivePlayer> getSortedAndWeightedOffensivePlayers(){

        offensivePlayers.sort((p1, p2) -> {
           double score1 = computeWeightedScore(p1);
           double score2 = computeWeightedScore(p2);
           return Double.compare(score1, score2);
       });

       return new ArrayList<>(offensivePlayers.subList(0, Math.min(9, offensivePlayers.size())));
    }

     double computeWeightedScore(OffensivePlayer offensivePlayer) {
        return this.scoringStrategy.score(offensivePlayer);
    }
}
