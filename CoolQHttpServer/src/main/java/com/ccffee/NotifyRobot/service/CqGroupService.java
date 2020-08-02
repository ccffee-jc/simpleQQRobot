package com.ccffee.NotifyRobot.service;

import com.ccffee.NotifyRobot.mbg.model.CqGroup;

public interface CqGroupService {
    CqGroup getGroupByGroupNum(String groupNum);

    CqGroup saveGroup(String groupNum);
}
