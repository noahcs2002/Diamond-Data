package com.dd.api.ai.agents;


import com.dd.api.ai.scoring.DefensivePlayerScoringStrategy;
import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.DefensivePlayer;
import io.micrometer.observation.annotation.Observed;
import org.hibernate.collection.spi.PersistentBag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefensivePlayerAgent {

    private final List<DefensivePlayer> defensivePlayers;
    private final ScoringStrategy<DefensivePlayer> defensivePlayerScoringStrategy;

    public DefensivePlayerAgent(List<DefensivePlayer> defensivePlayers, ScoringStrategy<DefensivePlayer> scoringStrategy) {
        Objects.requireNonNull(defensivePlayers);
        Objects.requireNonNull(scoringStrategy);
        this.defensivePlayers = defensivePlayers;
        this.defensivePlayerScoringStrategy = scoringStrategy;
    }

    public DefensivePlayerAgent(List<DefensivePlayer> players) {
        Objects.requireNonNull(players);
        this.defensivePlayers = players;
        this.defensivePlayerScoringStrategy = new DefensivePlayerScoringStrategy();
    }

    public List<DefensivePlayer> getSortedAndWeightedDefensivePlayers() {
        List<DefensivePlayer> defensivePlayers = new ArrayList<>();

        for (DefensivePlayer player : this.defensivePlayers) {
            if(defensivePlayers.size() < 9) {
                defensivePlayers.add(player);
            }
            else {
                if (Double.compare(computeWeightedScore(player), computeWeightedScore(defensivePlayers.get(defensivePlayers.size() - 1))) > 0){
                    DefensivePlayer min = null;

                    for(DefensivePlayer dp : this.defensivePlayers) {
                        if (min == null) {
                            min = dp;
                        }
                        else {
                            if (computeWeightedScore(dp) < computeWeightedScore(min)) {
                                min = dp;
                            }
                        }
                    }

                    this.defensivePlayers.remove(min);
                    this.defensivePlayers.add(player);
                    defensivePlayers.sort((o1, o2) -> Double.compare(computeWeightedScore(o1), computeWeightedScore(o2)));
                }
            }
        }
        return defensivePlayers;
    }

    double computeWeightedScore(DefensivePlayer defensivePlayer) {
        return this.defensivePlayerScoringStrategy.score(defensivePlayer);
    }
}