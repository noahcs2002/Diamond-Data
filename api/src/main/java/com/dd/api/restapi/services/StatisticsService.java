package com.dd.api.restapi.services;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.requestmodels.StatisticUpdateRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    private final OffensivePlayerService offensivePlayerService;

    @Autowired
    private final DefensivePlayerService defensivePlayerService;

    @Autowired
    private final PitcherService pitcherService;

    public boolean runUpdates(Long teamId, StatisticUpdateRequestModel update ) {

        try {
            update.getNewDefensivePlayers()
                    .forEach(p -> {
                        this.defensivePlayerService.updateDefensivePlayer(p.getId(), p);
                    });

            update.getNewPitchers()
                    .forEach(p -> {
                        this.pitcherService.updatePitcher(p.getId(), p);
                    });

            update.getNewOffensivePlayers()
                    .forEach(p -> {
                        this.offensivePlayerService.update(p.getId(), p);
                    });
            return true;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public StatisticsService(OffensivePlayerService offensivePlayerService, DefensivePlayerService defensivePlayerService, PitcherService pitcherService) {
        this.offensivePlayerService = offensivePlayerService;
        this.defensivePlayerService = defensivePlayerService;
        this.pitcherService = pitcherService;
    }

    public Pitcher updatePitcherStatistics(Pitcher pitcher) {
        pitcher.setEarnedRunAverage((double) pitcher.getEarnedRuns() * 9 / pitcher.getInningsPitched());

        if (pitcher.getSaveOpportunities() != 0) {
            pitcher.setSavePercentage((double) pitcher.getSaves() / pitcher.getSaveOpportunities());
        }
        else {
            pitcher.setSavePercentage(0.0);
        }

        if (pitcher.getInningsPitched() != 0) {
            pitcher.setWalksAndHitsPerInningPitched((double) (pitcher.getWalks() + pitcher.getHits()) / pitcher.getInningsPitched());
        }
        else {
            pitcher.setWalksAndHitsPerInningPitched(0.0);
        }

        int totalGames = pitcher.getWins() + pitcher.getLosses();
        if (totalGames != 0) {
            pitcher.setWinningPercentage((double) pitcher.getWins() / totalGames);
        }
        else {
            pitcher.setWinningPercentage(0.0);
        }

        this.pitcherService.updatePitcher(pitcher.getId(), pitcher);

        return pitcher;
    }

    public OffensivePlayer updateOffensivePlayerStatistics(OffensivePlayer offensivePlayer) {
        offensivePlayer.setBattingAverage((double) offensivePlayer.getHits() / (double) offensivePlayer.getPlateAppearances());
        offensivePlayer.setCaughtStealingPercentage( (double) offensivePlayer.getStolenBases() / (double) (offensivePlayer.getStolenBases() + offensivePlayer.getCaughtStealing()));
        offensivePlayer.setOnBasePercentage( (double) (offensivePlayer.getHits() + offensivePlayer.getWalks() + offensivePlayer.getIntentionalWalks() + offensivePlayer.getHitByPitch()) / (offensivePlayer.getAtBats() + offensivePlayer.getHitByPitch() + offensivePlayer.getWalks() + offensivePlayer.getIntentionalWalks() + offensivePlayer.getSacrificeFly()));
        offensivePlayer.setOnBasePlusSlugging(offensivePlayer.getOnBasePercentage() + offensivePlayer.getSluggingPercentage());
        offensivePlayer.setSluggingPercentage(offensivePlayer.getSingles() + (2* offensivePlayer.getDoubles()) + (3* offensivePlayer.getTriples()) + (4* offensivePlayer.getHomeRuns()) / (double) offensivePlayer.getAtBats());
        this.offensivePlayerService.update(offensivePlayer.getId(), offensivePlayer);
        return null;
    }

    public DefensivePlayer updateDefensivePlayerStatistics(DefensivePlayer defensivePlayer) {
        defensivePlayer.setCaughtStealingPercent(1 - (defensivePlayer.getStolenBasesAllowed() / defensivePlayer.getStolenBaseAttempts()));
        defensivePlayer.setTotalChances(defensivePlayer.getAssists() + defensivePlayer.getErrors() + defensivePlayer.getPutouts());
        defensivePlayer.setFieldingPercentage( (double) (defensivePlayer.getPutouts() + defensivePlayer.getAssists())/ (double) defensivePlayer.getTotalChances());
        this.defensivePlayerService.updateDefensivePlayer(defensivePlayer.getId(), defensivePlayer);
        return defensivePlayer;
    }
}
