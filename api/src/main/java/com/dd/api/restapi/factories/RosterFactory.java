package com.dd.api.restapi.factories;

import com.dd.api.restapi.models.Roster;
import com.dd.api.restapi.requestmodels.CreateRosterRequest;

import java.sql.Connection;
import java.sql.Date;
import java.util.UUID;

public class RosterFactory {
    
    public static Roster getAiRoster(Connection connection) {
        return new Roster(
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            Date.valueOf("2024-2-12"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"),
            UUID.fromString("3A9964A4-7AFB-47ED-8D38-0EB36523F3CD"));
    }
}
