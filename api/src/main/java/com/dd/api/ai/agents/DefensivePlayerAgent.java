package com.dd.api.ai.agents;


import com.dd.api.ai.scoring.DefensivePlayerScoringStrategy;
import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.DefensivePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefensivePlayerAgent {

    private final ArrayList<DefensivePlayer> defensivePlayers;
    private final ScoringStrategy<DefensivePlayer> defensivePlayerScoringStrategy;

    public DefensivePlayerAgent(ArrayList<DefensivePlayer> defensivePlayers, ScoringStrategy<DefensivePlayer> scoringStrategy) {
        Objects.requireNonNull(defensivePlayers);
        Objects.requireNonNull(scoringStrategy);
        this.defensivePlayers = defensivePlayers;
        this.defensivePlayerScoringStrategy = scoringStrategy;
    }

    public DefensivePlayerAgent(ArrayList<DefensivePlayer> players) {
        Objects.requireNonNull(players);
        this.defensivePlayers = players;
        this.defensivePlayerScoringStrategy = new DefensivePlayerScoringStrategy();
    }

    public List<DefensivePlayer> getSortedAndWeightedDefensivePlayers() {
       defensivePlayers.sort((p1, p2) -> {
           double score1 = computeWeightedScore(p1);
           double score2 = computeWeightedScore(p2);
           return Double.compare(score1, score2);
       });

       return new ArrayList<>(defensivePlayers.subList(0, Math.min(9, defensivePlayers.size())));
    }

    double computeWeightedScore(DefensivePlayer defensivePlayer) {
        return this.defensivePlayerScoringStrategy.score(defensivePlayer);
    }
}