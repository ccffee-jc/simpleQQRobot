package com.ccffee.NotifyRobot.service;

import com.ccffee.NotifyRobot.mbg.model.CqGroup;
import com.ccffee.NotifyRobot.mbg.model.CqUser;

public interface CqGroupMessageService {
    void saveMessage(String message, CqUser cqUser, CqGroup cqGroup);
}
