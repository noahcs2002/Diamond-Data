package com.dd.api.ai.agents;

import com.dd.api.ai.scoring.ScoringStrategy;
import com.dd.api.restapi.models.OffensivePlayer;
import org.hibernate.collection.spi.PersistentBag;

import java.util.List;

public class OffensivePlayerAgent {

    private final List<OffensivePlayer> offensivePlayers;
    private final ScoringStrategy<OffensivePlayer> scoringStrategy;

    public OffensivePlayerAgent(List<OffensivePlayer> offensivePlayers, ScoringStrategy<?> scoringStrategy) {
        this.offensivePlayers = offensivePlayers;
        this.scoringStrategy = (ScoringStrategy<OffensivePlayer>) scoringStrategy;
    }

    public List<OffensivePlayer> getSortedAndWeightedOffensivePlayers(){

        List<OffensivePlayer> players = new PersistentBag<>();
        players.sort((o1, o2) -> Double.compare(computeWeightedScore(o1), computeWeightedScore(o2)));

        for (OffensivePlayer player : this.offensivePlayers) {
            if(players.size() <= 9) {
                players.add(player);
            }
            else {
                if (Double.compare(computeWeightedScore(player), computeWeightedScore(players.get(players.size() - 1))) > 0){
                    players.remove(players.get(players.size() - 1));
                    players.add(player);
                    players.sort((o1, o2) -> Double.compare(computeWeightedScore(o1), computeWeightedScore(o2)));
                }
            }
        }
        return players;
    }

    private double computeWeightedScore(OffensivePlayer offensivePlayer) {
        return this.scoringStrategy.score(offensivePlayer);
    }

}
