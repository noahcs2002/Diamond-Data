package com.dd.api.builders;

import com.dd.api.models.Roster;

import java.util.Date;
import java.util.UUID;

public class RosterBuilder {
    private UUID id;
    private UUID managerId;
    private Date expiry;
    private UUID catcher;
    private UUID firstBase;
    private UUID secondBase;
    private UUID thirdBase;
    private UUID shortstop;
    private UUID leftField;
    private UUID rightField;
    private UUID centerField;
    private UUID startingPitcher;

    public RosterBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public RosterBuilder setManagerId(UUID managerId) {
        this.managerId = managerId;
        return this;
    }

    public RosterBuilder setExpiry(Date expiry) {
        this.expiry = expiry;
        return this;
    }

    public RosterBuilder setCatcher(UUID catcher) {
        this.catcher = catcher;
        return this;
    }

    public RosterBuilder setFirstBase(UUID firstBase) {
        this.firstBase = firstBase;
        return this;
    }

    public RosterBuilder setSecondBase(UUID secondBase) {
        this.secondBase = secondBase;
        return this;
    }

    public RosterBuilder setThirdBase(UUID thirdBase) {
        this.thirdBase = thirdBase;
        return this;
    }

    public RosterBuilder setShortstop(UUID shortstop) {
        this.shortstop = shortstop;
        return this;
    }

    public RosterBuilder setLeftField(UUID leftField) {
        this.leftField = leftField;
        return this;
    }

    public RosterBuilder setRightField(UUID rightField) {
        this.rightField = rightField;
        return this;
    }

    public RosterBuilder setCenterField(UUID centerField) {
        this.centerField = centerField;
        return this;
    }

    public RosterBuilder setStartingPitcher(UUID startingPitcher) {
        this.startingPitcher = startingPitcher;
        return this;
    }

    public Roster createRoster() {
        return new Roster(id, managerId, expiry, catcher, firstBase, secondBase, thirdBase, shortstop, leftField, rightField, centerField, startingPitcher);
    }
}