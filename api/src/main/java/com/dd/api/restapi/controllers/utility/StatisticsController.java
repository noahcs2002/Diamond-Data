package com.dd.api.restapi.controllers.utility;

import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.requestmodels.StatisticUpdateRequestModel;
import com.dd.api.restapi.services.StatisticsService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diamond-data/api/stats")
public class StatisticsController {

    @Autowired
    private final StatisticsService statisticsService;

    @Autowired
    private final Validator validator;

    @Autowired
    public StatisticsController(StatisticsService statisticsService, Validator validator) {
        this.statisticsService = statisticsService;
        this.validator = validator;
    }

    @PutMapping
    @RequestMapping("/record-new-game")
    public boolean recordNewGame(@RequestParam Long userId,
                                 @RequestParam Long teamId,
                                 @RequestBody StatisticUpdateRequestModel updates) throws NoAccessPermittedException {

        if (!this.validator.validateTeam(userId, teamId)) {
            throw new NoAccessPermittedException(userId);
        }
        return this.statisticsService.runUpdates(userId, updates);
    }
}
