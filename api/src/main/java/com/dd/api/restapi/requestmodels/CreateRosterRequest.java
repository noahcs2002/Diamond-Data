package com.dd.api.restapi.requestmodels;

import java.util.Map;
import java.util.UUID;

public class CreateRosterRequest {
	
    private final Map<String, UUID> memberIds;
    
    /**
     * USAGE:
     * Include the following:
     * managerId
     * catcher
     * firstBase
     * secondBase
     * thirdBase
     * shortStop
     * leftField
     * rightField
     * centerField
     * startingPitcher
     * @param uuids The map of UUIDS.
     */
    public CreateRosterRequest(Map<String, UUID> uuids) {
	this.memberIds = uuids;
    }
    
    public Map<String, UUID> getMemberIds() {
	return memberIds;
    }
}
