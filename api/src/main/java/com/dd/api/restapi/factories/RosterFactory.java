package com.dd.api.restapi.factories;

import com.dd.api.restapi.models.Roster;
import com.dd.api.restapi.requestmodels.CreateRosterRequest;

import java.sql.Connection;

public class RosterFactory {
    public static Roster createRoster(CreateRosterRequest rosterRequest, Connection connection) {
	
	String sql = """
	    INSERT INTO sp24.dd_rosters
	    (id, managerId, expiry, catcher, firstBase,
	    secondBase, thirdBase, shortStop, leftField,
	    rightField, centerField, startingPitcher, ghosted_date) VALUES
	    (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
	""";
	
    }
}
