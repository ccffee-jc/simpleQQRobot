package com.ccffee.NotifyRobot.service;

import java.util.HashMap;

public interface CqMessageNoATService {
    HashMap messageDistributor(String message, String groupId, String userId);
}
