package com.dd.api.ai;


import com.dd.api.restapi.models.DefensivePlayer;
import org.hibernate.collection.spi.PersistentBag;

import java.util.List;

public class DefensivePlayerAgent {

    private final List<DefensivePlayer> defensivePlayers;

    public DefensivePlayerAgent(List<DefensivePlayer> defensivePlayers) {
        this.defensivePlayers = defensivePlayers;
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
        return
                (double) defensivePlayer.getAssists() * ((double) 1 /12)+
                        defensivePlayer.getCaughtStealingPercent() * ((double) 1 /12)+
                        (double) defensivePlayer.getDoublePlay() * ((double) 1 /12)+
                        (double) defensivePlayer.getErrors() * ((double) 1 /12) +
                        defensivePlayer.getFieldingPercentage() * ((double) 1 /12) +
                        (double) defensivePlayer.getInningsPlayed() * ((double) 1 /12) +
                        (double) defensivePlayer.getOuts() * ((double) 1 /12) +
                        (double) defensivePlayer.getOutfieldAssists() * ((double) 1 /12) +
                        (double) defensivePlayer.getPassedBalls() * ((double) 1 /12) +
                        (double) defensivePlayer.getPutouts() * ((double) 1 /12) +
                        (double) defensivePlayer.getTotalChances() * ((double) 1 /12) +
                        (double) defensivePlayer.getTriplePlays() * ((double) 1 /12);
    }
}