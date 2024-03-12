package com.dd.api.restapi.requestmodels;

public record CreateLineupRequestModel(Long team, Long firstBase, Long secondBase, Long thirdBase, Long shortstop, Long leftField, Long rightField, Long centerField, Long catcher, Long pitcher) {
}
