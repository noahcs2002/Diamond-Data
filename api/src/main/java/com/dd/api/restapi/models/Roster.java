package com.dd.api.restapi.models;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Roster {
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

    public Roster(UUID id, UUID managerId, Date expiry, UUID catcher, UUID firstBase, UUID secondBase, UUID thirdBase, UUID shortstop, UUID leftField, UUID rightField, UUID centerField, UUID startingPitcher) {
        this.id = id;
        this.managerId = managerId;
        this.expiry = expiry;
        this.catcher = catcher;
        this.firstBase = firstBase;
        this.secondBase = secondBase;
        this.thirdBase = thirdBase;
        this.shortstop = shortstop;
        this.leftField = leftField;
        this.rightField = rightField;
        this.centerField = centerField;
        this.startingPitcher = startingPitcher;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public UUID getCatcher() {
        return catcher;
    }

    public void setCatcher(UUID catcher) {
        this.catcher = catcher;
    }

    public UUID getFirstBase() {
        return firstBase;
    }

    public void setFirstBase(UUID firstBase) {
        this.firstBase = firstBase;
    }

    public UUID getSecondBase() {
        return secondBase;
    }

    public void setSecondBase(UUID secondBase) {
        this.secondBase = secondBase;
    }

    public UUID getThirdBase() {
        return thirdBase;
    }

    public void setThirdBase(UUID thirdBase) {
        this.thirdBase = thirdBase;
    }

    public UUID getShortstop() {
        return shortstop;
    }

    public void setShortstop(UUID shortstop) {
        this.shortstop = shortstop;
    }

    public UUID getLeftField() {
        return leftField;
    }

    public void setLeftField(UUID leftField) {
        this.leftField = leftField;
    }

    public UUID getRightField() {
        return rightField;
    }

    public void setRightField(UUID rightField) {
        this.rightField = rightField;
    }

    public UUID getCenterField() {
        return centerField;
    }

    public void setCenterField(UUID centerField) {
        this.centerField = centerField;
    }

    public UUID getStartingPitcher() {
        return startingPitcher;
    }

    public void setStartingPitcher(UUID startingPitcher) {
        this.startingPitcher = startingPitcher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roster roster = (Roster) o;
        return Objects.equals(id, roster.id) && Objects.equals(managerId, roster.managerId) && Objects.equals(expiry, roster.expiry) && Objects.equals(catcher, roster.catcher) && Objects.equals(firstBase, roster.firstBase) && Objects.equals(secondBase, roster.secondBase) && Objects.equals(thirdBase, roster.thirdBase) && Objects.equals(shortstop, roster.shortstop) && Objects.equals(leftField, roster.leftField) && Objects.equals(rightField, roster.rightField) && Objects.equals(centerField, roster.centerField) && Objects.equals(startingPitcher, roster.startingPitcher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, managerId, expiry, catcher, firstBase, secondBase, thirdBase, shortstop, leftField, rightField, centerField, startingPitcher);
    }
}
