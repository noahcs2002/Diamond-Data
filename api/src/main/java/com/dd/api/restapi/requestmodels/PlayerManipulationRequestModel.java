package com.dd.api.restapi.requestmodels;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.OffensivePlayer;

/**
 * Used to create or update a player object
 * @param offensivePlayer
 * @param defensivePlayer
 */
public record PlayerManipulationRequestModel(OffensivePlayer offensivePlayer, DefensivePlayer defensivePlayer, String firstName, String lastName) {
}