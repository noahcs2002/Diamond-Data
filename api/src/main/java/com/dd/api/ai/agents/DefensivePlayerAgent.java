package com.dd.api.ai.agents;


import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.DefensivePlayer;
import org.hibernate.collection.spi.PersistentBag;

import java.util.List;

public class DefensivePlayerAgent {

    private final List<DefensivePlayer> defensivePlayers;
    private final ScoringStrategy<DefensivePlayer> defensivePlayerScoringStrategy;

    public DefensivePlayerAgent(List<DefensivePlayer> defensivePlayers, ScoringStrategy<DefensivePlayer> scoringStrategy) {
        this.defensivePlayers = defensivePlayers;
        this.defensivePlayerScoringStrategy = scoringStrategy;
    }

    public List<DefensivePlayer> getSortedAndWeightedDefensivePlayers() {
        List<DefensivePlayer> defensivePlayers = new PersistentBag<>();
        defensivePlayers.sort((o1, o2) -> Double.compare(computeWeightedScore(o1), computeWeightedScore(o2)));

        for (DefensivePlayer player : this.defensivePlayers) {
            if(defensivePlayers.size() <= 9) {
                defensivePlayers.add(player);
            }
            else {
                if (Double.compare(computeWeightedScore(player), computeWeightedScore(defensivePlayers.get(defensivePlayers.size() - 1))) > 0){
                    defensivePlayers.remove(defensivePlayers.get(defensivePlayers.size() - 1));
                    defensivePlayers.add(player);
                    defensivePlayers.sort((o1, o2) -> Double.compare(computeWeightedScore(o1), computeWeightedScore(o2)));
                }
            }
        }
        return defensivePlayers;
    }

    private double computeWeightedScore(DefensivePlayer defensivePlayer) {
        return this.defensivePlayerScoringStrategy.score(defensivePlayer);
    }
}